//package bitMap
//
//
//import java.math.BigInteger
//
//@Deprecated("FactoryBitMutableList")
//
//class BitWrapper(
//    val bitMapListInterface: BitMapListInterface<Int, Long, BigInteger> = BitMutableList(),
//    val mutableList: MutableList<Boolean> = mutableListOf(),
//    override var size: Int = 0
//) : BitMapListInterface<Int, Long, BigInteger> {
//    override fun add(element: Boolean): Boolean {
//        bitMapListInterface.add(element)
//        val add = mutableList.add(element)
//        if (add)
//            this.size++
//        return add
//    }
//
//    override fun add(index: Int, element: Boolean) {
//        bitMapListInterface.add(index, element)
//        mutableList.add(index, element)
//        this.size++
//    }
//
//    override fun addAll(index: Int, elements: Collection<Boolean>): Boolean {
//        bitMapListInterface.addAll(index, elements)
//        val addAll = mutableList.addAll(index, elements)
//        this.size = mutableList.size
//        return addAll
//    }
//
//    override fun addAll(elements: Collection<Boolean>): Boolean {
//        bitMapListInterface.addAll(elements)
//        val addAll = mutableList.addAll(elements)
//        this.size = mutableList.size
//        return addAll
//    }
//
//    override fun clear() {
//        bitMapListInterface.clear()
//        this.size = 0
//        return mutableList.clear()
//    }
//
//    override fun contains(element: Boolean): Boolean {
//        bitMapListInterface.contains(element)
//        return mutableList.contains(element)
//    }
//
//    override fun containsAll(elements: Collection<Boolean>): Boolean {
//        bitMapListInterface.containsAll(elements)
//        return mutableList.containsAll(elements)
//    }
//
//    override fun get(index: Int): Boolean = mutableList.get(index)
//
//    override fun indexOf(element: Boolean): Int = mutableList.indexOf(element)
//
//    override fun isEmpty(): Boolean = mutableList.isEmpty()
//    override fun iterator(): MutableIterator<Boolean> = object : MutableIterator<Boolean> {
//
//        val bitMapListAbstractIterator = this@BitWrapper.bitMapListInterface.iterator()
//        val mutableListIterator = this@BitWrapper.mutableList.iterator()
//
//        override fun hasNext(): Boolean = mutableListIterator.hasNext()
//
//        override fun next(): Boolean {
//            bitMapListAbstractIterator.next()
//            return mutableListIterator.next()
//        }
//
//        override fun remove() {
//            bitMapListAbstractIterator.remove()
//            mutableListIterator.remove()
//            this@BitWrapper.size = mutableList.size
//        }
//
//    }
//
//    override fun lastIndexOf(element: Boolean): Int = mutableList.lastIndexOf(element)
//
//    private fun factoryListIterator(index: Int = 0): MutableListIterator<Boolean> =
//        object : MutableListIterator<Boolean> {
//
//            val bitMapListAbstractIterator =
//                if (index == 0) this@BitWrapper.bitMapListInterface.listIterator() else this@BitWrapper.bitMapListInterface.listIterator(
//                    index
//                )
//            val mutableListIterator =
//                if (index == 0) this@BitWrapper.mutableList.listIterator() else this@BitWrapper.mutableList.listIterator(
//                    index
//                )
//
//
//            override fun add(element: Boolean) {
//                bitMapListAbstractIterator.add(element)
//                mutableListIterator.add(element)
//                this@BitWrapper.size = mutableList.size
//            }
//
//            override fun hasNext(): Boolean = mutableListIterator.hasNext()
//
//            override fun hasPrevious(): Boolean = mutableListIterator.hasPrevious()
//
//            override fun next(): Boolean {
//                bitMapListAbstractIterator.next()
//                return mutableListIterator.next()
//            }
//
//            override fun nextIndex(): Int = mutableListIterator.nextIndex()
//
//            override fun previous(): Boolean {
//                bitMapListAbstractIterator.previous()
//                return mutableListIterator.previous()
//            }
//
//            override fun previousIndex(): Int = mutableListIterator.previousIndex()
//
//            override fun remove() {
//                bitMapListAbstractIterator.remove()
//                mutableListIterator.remove()
//                this@BitWrapper.size = mutableList.size
//            }
//
//            override fun set(element: Boolean) {
//                bitMapListAbstractIterator.set(element)
//                mutableListIterator.set(element)
//            }
//
//        }
//
//    override fun listIterator(): MutableListIterator<Boolean> = factoryListIterator()
//
//    override fun listIterator(index: Int): MutableListIterator<Boolean> = factoryListIterator(index)
//
//    override fun remove(element: Boolean): Boolean {
//        bitMapListInterface.remove(element)
//        val remove = mutableList.remove(element)
//        this.size--
//        return remove
//    }
//
//    override fun removeAll(elements: Collection<Boolean>): Boolean {
//        bitMapListInterface.removeAll(elements)
//        val removeAll = mutableList.removeAll(elements)
//        this@BitWrapper.size = mutableList.size
//        return removeAll
//    }
//
//    override fun removeAt(index: Int): Boolean {
//        (bitMapListInterface as MutableList<Boolean>).removeAt(index)
//        val removeAt = mutableList.removeAt(index)
//        this.size--
//        return removeAt
//    }
//
//    override fun retainAll(elements: Collection<Boolean>): Boolean {
//        bitMapListInterface.retainAll(elements)
//        val retainAll = mutableList.retainAll(elements)
//        this.size = mutableList.size
//        return retainAll
//    }
//
//    override fun set(index: Int, element: Boolean): Boolean {
//        (bitMapListInterface as MutableList<Boolean>).set(index, element)
//        return mutableList.set(index, element)
//    }
//
//    override fun subList(fromIndex: Int, toIndex: Int): MutableList<Boolean> {
//        bitMapListInterface.subList(fromIndex, toIndex)
//        return mutableList.subList(fromIndex, toIndex)
//    }
//
//    override fun copy(): BitMapInterface<Int, Long, BigInteger> = BitWrapper(bitMapListInterface, mutableList)
//    override fun getContentBit(): BigInteger = bitMapListInterface.getContentBit()
//
//    override fun getLimit(): Int = bitMapListInterface.getLimit()
//
////    override fun powInstance(index: Int): BigInteger = bitMapListInterface.powInstance(index)
////
////    override fun getInstance(input: Long): BigInteger = bitMapListInterface.getInstance(input)
//}