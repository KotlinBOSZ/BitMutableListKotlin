package bitMap

import java.lang.IllegalArgumentException
import kotlin.IndexOutOfBoundsException


open class BitMutableList<I, O>(
    var content: O,
    val and: O.(other: O) -> O,
    val shl: O.(n: Int) -> O,
    val shr: O.(n: Int) -> O,
    val minusOperator: O.(other: O) -> O,
    val plusOperator: O.(other: O) -> O,
    val powInstance: (index: Int) -> O,
    val getInstance: (input: I) -> O,
    val toInput: Int.() -> I,
    override var size: Int = 0,
    val indexLimit: Int = BitMapInterface.UNLIMITED,
    val getInstance0: O = getInstance(0.toInput())
) : BitMapListInterface<Int, I, O> {
    companion object{}

    init {
        if (size == indexLimit && indexLimit>=0) throw IndexOutOfBoundsException()
    }
    private operator fun O.minus(other: O): O = minusOperator(other)
    private operator fun O.plus(other: O): O = plusOperator(other)

    override fun contains(element: Boolean): Boolean {
        if (size <= 0) return false
        val isAllTrue = content == this.getInstance((2 * size - 1).toInput())
        val isAllFalse = content == this.getInstance0
        return when {
            element && isAllTrue -> true
            !element && isAllTrue -> false
            element && isAllFalse -> false
            !element && isAllFalse -> true
            element && !isAllFalse -> true
            !element && !isAllTrue -> true
            else -> throw RuntimeException()
        }
    }

    override fun containsAll(elements: Collection<Boolean>): Boolean = elements.toSet().all { contains(it) }

    override fun add(element: Boolean): Boolean = powInstance(size).let {
        if (size == getLimit() && getLimit()>=0) throw IndexOutOfBoundsException()
        if ((content.and(it)) != it) {
            if (element) content += it
            size++
            true
        } else {
            false
        }
    }

    override fun add(index: Int, element: Boolean) {
        when {
            index > size || (size == getLimit()&& getLimit()>=0)-> throw IndexOutOfBoundsException()
            index == size -> add(element)
            index == 0 -> content = (content.shl(1)).also { size++; if (element) it + this.getInstance(1.toInput()) }
            else -> {
                val subList = subList(0, index)
                if (subList is BitMutableList<*, *>) {
                    content = (content.shr(index)).shl(index + 1)
                    content += subList.content as O
                    if (element) content += powInstance(index)
                    size++
                } else throw RuntimeException()
            }
        }
    }

    override fun addAll(index: Int, elements: Collection<Boolean>): Boolean {
        return when {
            index > size || (size == getLimit()&& getLimit()>=0)-> throw IndexOutOfBoundsException()
            index == size -> addAll(elements)
            index == 0 -> {
                content = (content.shl(elements.size))
                if (elements is BitMutableList<*, *>) {
                    content += elements.content as O
                    size += elements.size
                    return true
                }

                elements.forEachIndexed { index, e ->
                    if (e) content += powInstance(index)
                    size++
                }
                true
            }

            else -> {
                val subList = subList(0, index)
                content = content.shr(index)
                size -= index
                if (subList is BitMutableList<*, *>) {
                    addAll(0, elements)
                    addAll(0, subList)
                    true
                } else throw RuntimeException()
            }
        }
    }

    override fun addAll(elements: Collection<Boolean>): Boolean = elements.map { add(it) }.all { it }

    override fun clear() {
        size = 0
        content = this.getInstance(0.toInput())
    }

    override fun get(index: Int): Boolean = if (index >= size) {
        throw IndexOutOfBoundsException()
    } else {
        powInstance(index).let { (content.and(it)) == it }
    }


    override fun isEmpty(): Boolean = size == 0 && content == this.getInstance0

    override fun iterator(): MutableIterator<Boolean> = object : MutableIterator<Boolean> {
        var next = 0
        var isRemovable = false
        override fun hasNext(): Boolean = next < size

        override fun next(): Boolean {
            if (!hasNext()) throw NoSuchElementException()
            val b = this@BitMutableList[next]
            next++;
            isRemovable = true
            return b;
        }

        override fun remove() {
            if (!isRemovable) throw IllegalStateException()
            next--
            removeAt(next)
            isRemovable = false
        }

    }

    private fun factoryListIterator(index: Int = 0): MutableListIterator<Boolean> =
        if (index > size) throw IndexOutOfBoundsException() else object : MutableListIterator<Boolean> {

            var next = index
            var isRemovable = false
            var isPreviousLast = false

            override fun add(element: Boolean) {
                try {
                    next++
                    add(next - 1, element)
                }catch (e:IndexOutOfBoundsException){
                    next--
                }
            }

            override fun hasNext(): Boolean = next < size

            override fun hasPrevious(): Boolean = next > 0

            override fun next(): Boolean {
                if (!hasNext()) throw NoSuchElementException()
                next++
                isRemovable = true
                isPreviousLast = false
                return get(next - 1)

            }

            override fun nextIndex(): Int = next

            override fun previous(): Boolean {
                if (!hasPrevious()) throw NoSuchElementException()
                next--
                isRemovable = true
                isPreviousLast = true
                return get(next)
            }

            override fun previousIndex(): Int = next - 1

            override fun remove() {
                when {
                    !isRemovable -> throw IllegalStateException()
                    next == 0 -> {
                        isRemovable = false
                        removeAt(next)
                    }

                    isPreviousLast -> {
                        isRemovable = false
                        removeAt(next)
                    }

                    else -> {
                        isRemovable = false
                        next--
                        removeAt(next)
                    }
                }

            }

            override fun set(element: Boolean) {
                set(next - 1, element)
            }

        }

    override fun listIterator(): MutableListIterator<Boolean> = factoryListIterator()

    override fun listIterator(index: Int): MutableListIterator<Boolean> = factoryListIterator(index)

    override fun removeAt(index: Int): Boolean {
        when {
            index >= size -> throw IndexOutOfBoundsException()
            index == 0 -> {
                val tmp = get(index)
                content = (content.shr(1))
                size--
                return tmp
            }

            else -> {
                val subList = subList(0, index)
                val tmp = get(index)
                if (subList is BitMutableList<*, *>) {
                    content = (content.shr(index + 1))
                    size -= index + 1
                    addAll(0, subList)
                } else throw RuntimeException()
                return tmp
            }
        }
    }


    override fun subList(fromIndex: Int, toIndex: Int): MutableList<Boolean> = when {
        fromIndex > toIndex -> throw IllegalArgumentException()
        toIndex > size -> throw IndexOutOfBoundsException()
        else -> {
            BitMutableList(
                (content.shr(fromIndex)).and(this.getInstance((2 * (toIndex - fromIndex) - 1).toInput())),
                and,
                shl,
                shr,
                minusOperator,
                plusOperator,
                powInstance,
                getInstance,
                toInput,
                toIndex - fromIndex
            )
        }
    }

    override fun copy(): BitMapInterface<Int, I, O> = BitMutableList(
        content, and, shl, shr, minusOperator, plusOperator, powInstance, getInstance, toInput, size
    )


    override fun getContentBit(): O = content

    override fun getLimit(): Int = indexLimit
    override fun addAt(index: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun setSizeAt(index: Int) {
        TODO("Not yet implemented")
    }

    override fun set(index: Int, element: Boolean): Boolean = if (index >= size) {
        throw IndexOutOfBoundsException()
    } else {
        if (get(index)) {
            if (!element) content -= powInstance(index)
            true
        } else {
            if (element) content += powInstance(index)
            false
        }
    }

    override fun retainAll(elements: Collection<Boolean>): Boolean = elements.toSet().let { toSet ->
        if (toSet.size >= 2) return@let false
        var tmp = false
        this.iterator().let {
            while (it.hasNext()) {
                if (!toSet.contains(it.next())) {
                    it.remove()
                    tmp = true
                }
            }
        }
        return@let tmp
    }

    override fun removeAll(elements: Collection<Boolean>): Boolean {
        val toSet = elements.toSet()
        if (!containsAll(toSet)) return false
        iterator().let {
            while (it.hasNext()) {
                if (toSet.contains(it.next())) {
                    it.remove()
                }
            }
        }
        return true
    }

    override fun remove(element: Boolean): Boolean = iterator().let {
        while (it.hasNext()) {
            if (it.next() == element) {
                it.remove()
                return@let true
            }
        }
        return@let false
    }

    override fun lastIndexOf(element: Boolean): Int {
        for (i in size - 1 downTo 0) {
            if (get(i) == element) return i
        }
        return -1
    }

    override fun indexOf(element: Boolean): Int {
        for (i in 0..<size) {
            if (get(i) == element) return i
        }
        return -1
    }
}






