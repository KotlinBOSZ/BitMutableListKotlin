package bitMap

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.lang.IndexOutOfBoundsException
import java.math.BigInteger

class BitWrapperTest{

    var L: BitWrapper = BitWrapper()
    var NL = mutableListOf<Boolean>()

    @BeforeEach
    fun startUp() {
        L = BitWrapper()
        NL = mutableListOf()
    }

    @Test
    fun contains() {
        assertFalse(L.contains(true))
        assertFalse(L.contains(false))
        L.add(true)
        assertTrue(L.contains(true))
        assertFalse(L.contains(false))
        L[0] = false
        assertFalse(L.contains(true))
        assertTrue(L.contains(false))
        L.add(true)
        assertTrue(L.contains(true))
        assertTrue(L.contains(false))
    }

    @Test
    fun containsAll() {
        assertFalse(L.containsAll(listOf(true)))
        assertFalse(L.containsAll(listOf(false)))
        assertFalse(L.containsAll(listOf(false, true, false)))
        L.add(true)
        assertTrue(L.containsAll(listOf(true)))
        assertFalse(L.containsAll(listOf(false)))
        assertFalse(L.containsAll(listOf(false, true, false)))
        L[0] = false
        assertFalse(L.containsAll(listOf(true)))
        assertTrue(L.containsAll(listOf(false)))
        assertFalse(L.containsAll(listOf(false, true, false)))
        L.add(true)
        assertTrue(L.containsAll(listOf(true)))
        assertTrue(L.containsAll(listOf(false)))
        assertTrue(L.containsAll(listOf(false, true, false)))
    }

    @Test
    fun Size() {
        assert(L.size == NL.size)
        L.add(true)
        NL.add(true)
        assert(L.size == NL.size)
        L.add(1, true)
        NL.add(1, true)
        assert(L.size == NL.size)
        L.add(0, true)
        NL.add(0, true)
        assert(L.size == NL.size)
        L.set(1, false)
        NL.set(1, false)
        assert(L.size == NL.size)
        L.addAll(listOf(true, false, true))
        NL.addAll(listOf(true, false, true))
        assert(L.size == NL.size)
        L.addAll(1, listOf(true, false))
        NL.addAll(1, listOf(true, false))
        assert(L.size == NL.size)
        L.removeAt(1)
        NL.removeAt(1)
        assert(L.size == NL.size)
        L.removeAt(0)
        NL.removeAt(0)
        assert(L.size == NL.size)
        L.clear()
        NL.clear()
        assert(L.size == NL.size)
    }

    @Test
    fun Add() {
        L.add(true)
        NL.add(true)
        assert(L[0] == NL[0])
        L.add(false)
        NL.add(false)
        assert(L[1] == NL[1])
        L.add(true)
        NL.add(true)
        assert(L.get(2) == NL.get(2))
    }

    @Test
    fun Add2() {
        L.add(0, true)
        NL.add(0, true)
        assert(L[0] == NL[0])
        L.add(1, false)
        NL.add(1, false)
        assert(L[1] == NL[1])
        L.add(1, true)
        NL.add(1, true)
        assert(L.get(2) == NL.get(2))
        assertThrows(IndexOutOfBoundsException::class.java, { L.add(4, true) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.add(5, true) })

    }

    @Test
    fun addAll() {
        val listOf = listOf(false, true, false, true, true)
        L.addAll(listOf)
        NL.addAll(listOf)
        assert(L[0] == NL[0])
        assert(L[1] == NL[1])
        assert(L[2] == NL[2])
        assert(L[3] == NL[3])
        assert(L[4] == NL[4])
    }

    @Test
    fun addAll2() {
        val listOf = listOf(false, true, false, true, true)
        L.addAll(listOf)
        NL.addAll(listOf)
        L.addAll(2, listOf)
        NL.addAll(2, listOf)
        assert(L[0] == NL[0])
        assert(L[1] == NL[1])
        assert(L[2] == NL[2])
        assert(L[3] == NL[3])
        assert(L[4] == NL[4])
        assert(L[5] == NL[5])
        assert(L[6] == NL[6])
        assert(L[7] == NL[7])
        assert(L[8] == NL[8])
        assert(L[9] == NL[9])
        assertThrows(IndexOutOfBoundsException::class.java, { L.addAll(11, listOf) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.addAll(12, listOf) })
    }

    @Test
    fun clear() {
        L.add(true)
        L.clear()
        assert(L.size == 0)
    }

    @Test
    fun get() {
        L = BitWrapper()
        L.add(true)
        L.add(false)
        assert(L.get(0) == true)
        assert(L.get(1) == false)
        assertThrows(IndexOutOfBoundsException::class.java, { L.get(2) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.get(3) })
    }

    @Test
    fun isEmpty() {
        assertTrue(L.isEmpty())
        L.add(true)
        assertFalse(L.isEmpty())
    }

    @Test
    operator fun iterator() {
        val elements = listOf(true, false, true)
        L.addAll(elements)
        val iterator = L.iterator()
        assertTrue(iterator.hasNext())

        assert(iterator.next() == elements[0])

        iterator.remove()

        assertTrue(iterator.hasNext())

        assert(iterator.next() == elements[1])
        assert(iterator.next() == elements[2])

        assertFalse(iterator.hasNext())

        iterator.remove()

        assertThrows(NoSuchElementException::class.java, { iterator.next() })
        assertThrows(IllegalStateException::class.java, { iterator.remove() })
        assertThrows(IndexOutOfBoundsException::class.java, { L.get(1) })
    }

    @Test
    fun listIterator() {
        val elements = listOf(true, false, true, false, false, true, false)
        L.addAll(elements)
        val listIterator = L.listIterator()

        assertThrows(NoSuchElementException::class.java, { listIterator.previous() })
        assertThrows(IllegalStateException::class.java, { listIterator.remove() })
        assert(listIterator.previousIndex() == -1)
        assert(listIterator.nextIndex() == 0)
        assertFalse(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        assert(listIterator.next() == elements[0])

        assert(listIterator.previousIndex() == 0)
        assert(listIterator.nextIndex() == 1)
        assertTrue(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        assert(listIterator.previous() == elements[0])

        assertThrows(NoSuchElementException::class.java, { listIterator.previous() })
        listIterator.remove()
        assertThrows(IllegalStateException::class.java, { listIterator.remove() })
        assert(listIterator.previousIndex() == -1)
        assert(listIterator.nextIndex() == 0)
        assertFalse(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        assert(listIterator.next() == elements[1])
        assert(listIterator.next() == elements[2])

        assert(listIterator.previousIndex() == 1)
        assert(listIterator.nextIndex() == 2)
        assertTrue(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        listIterator.remove()

        assert(listIterator.previousIndex() == 0)
        assert(listIterator.nextIndex() == 1)
        assertTrue(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        assert(listIterator.next() == elements[3])
        assert(listIterator.next() == elements[4])
        assert(listIterator.next() == elements[5])
        assert(listIterator.next() == elements[6])

        assertThrows(NoSuchElementException::class.java, { listIterator.next() })

        assert(listIterator.previousIndex() == 4)
        assert(listIterator.nextIndex() == 5)
        assertTrue(listIterator.hasPrevious())
        assertFalse(listIterator.hasNext())

        listIterator.set(true)

        assert(listIterator.previousIndex() == 4)
        assert(listIterator.nextIndex() == 5)
        assertTrue(listIterator.hasPrevious())
        assertFalse(listIterator.hasNext())

        assertTrue(listIterator.previous())

        assert(listIterator.previousIndex() == 3)
        assert(listIterator.nextIndex() == 4)
        assertTrue(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        listIterator.add(false)

        assert(listIterator.previousIndex() == 4)
        assert(listIterator.nextIndex() == 5)
        assertTrue(listIterator.hasPrevious())
        assertTrue(listIterator.hasNext())

        assertFalse(listIterator.previous())
        assertFalse(listIterator.next())
        assertTrue(listIterator.next())

        assert(listIterator.previousIndex() == 5)
        assert(listIterator.nextIndex() == 6)
        assertTrue(listIterator.hasPrevious())
        assertFalse(listIterator.hasNext())

        listIterator.remove()
        assertThrows(IllegalStateException::class.java, { listIterator.remove() })

        listIterator.previous()

        listIterator.remove()
        assertThrows(IllegalStateException::class.java, { listIterator.remove() })
    }

    @Test
    fun ListIterator2() {
        val listOf = listOf(false, false, true)
        L.addAll(listOf)
        assertThrows(IndexOutOfBoundsException::class.java, { L.listIterator(4) })
        val listIterator = L.listIterator(3)
        assertFalse(listIterator.hasNext())
        assertTrue(listIterator.previous())
        listIterator.remove()
        assertFalse(listIterator.previous())
        assertFalse(listIterator.previous())
    }

    @Test
    fun removeAt() {
        L.addAll(listOf(true, false, true))
        NL.addAll(listOf(true, false, true))
        NL.removeAt(0)
        L.removeAt(0)
        NL.removeAt(1)
        L.removeAt(1)
        assert(L[0] == NL[0])
        assertThrows(IndexOutOfBoundsException::class.java, { L.removeAt(1) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.removeAt(2) })
    }

    @Test
    fun subList() {
        val listOf = listOf(false, true, false, true, true)
        L.addAll(listOf)
        NL.addAll(listOf)
        val subList = L.subList(1, 4)
        val subList1 = NL.subList(1, 4)
        assert(subList[0] == subList1[0])
        assert(subList[1] == subList1[1])
        assert(subList[2] == subList1[2])
        assert(subList.size == subList1.size)
        assertThrows(IllegalArgumentException::class.java, { L.subList(5, 4) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.subList(1, 6) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.subList(1, 7) })
    }

    @Test
    fun set() {
        L.add(true)
        assertTrue(L.set(0, false))
        assertFalse(L[0])
        assertThrows(IndexOutOfBoundsException::class.java, { L.set(1, false) })
        assertThrows(IndexOutOfBoundsException::class.java, { L.set(2, false) })
    }

    @Test
    fun retainAll() {
        val elements = listOf(false, false, false)
        L.addAll(elements)
        assertFalse(L.retainAll(listOf(false)))
        assertTrue(L.retainAll(listOf(true)))
        assert(L.size == 0)
        L.addAll(listOf(false, true, false))
        assertFalse(L.retainAll(listOf(false, true)))
        assertTrue(L.retainAll(listOf(true)))
        assertTrue(L[0])
        L.addAll(listOf(false, true, false))
        assertTrue(L.retainAll(listOf(false)))
        assertFalse(L[0])
        assertFalse(L[1])
    }

    @Test
    fun removeAll() {
        L.addAll(listOf(true, true))
        assertFalse(L.removeAll(listOf(false)))
        assertTrue(L.removeAll(listOf(true)))
        assertTrue(L.isEmpty())
        L.addAll(listOf(true, false, true, false))
        assertTrue(L.removeAll(listOf(true, false)))
        assertTrue(L.isEmpty())
        L.addAll(listOf(true, false, true, false))
        assertTrue(L.removeAll(listOf(true)))
        assertTrue(L.size == 2)
    }

    @Test
    fun remove() {
        L.addAll(listOf(true, true))
        assertFalse(L.remove(false))
        assertTrue(L.remove(true))
        assertTrue(L.remove(true))
        assertTrue(L.isEmpty())

        L.addAll(listOf(true, false, true, false))

        assertTrue(L.remove(true))
        assertFalse(L[0])
        assertTrue(L.remove(true))
        assertTrue(L.size == 2)
    }

    @Test
    fun lastIndexOf() {
        L.addAll(listOf(true, false, true, false))
        assert(L.lastIndexOf(true) == 2)
        assert(L.lastIndexOf(false) == 3)
    }

    @Test
    fun indexOf() {
        L.addAll(listOf(true, false, true, false))
        assert(L.indexOf(true) == 0)
        assert(L.indexOf(false) == 1)
    }

    @Test
    fun t() {

        var start : Long = 0
        var end : Long = 0

        BitPowBigInteger.pow(100)

        start = System.nanoTime()
        for (i in 0..100){
            NL.add(true)
        }
        end = System.nanoTime()
        println(end - start)

        start = System.nanoTime()
        for (i in 0..100){
            L.add(true)
        }
        end = System.nanoTime()
        println(end - start)

        start = System.nanoTime()
        for (i in 0..100){
            NL.get(1)
        }
        end = System.nanoTime()
        println(end - start)

        start = System.nanoTime()
        for (i in 0..100){
            L.get(1)
        }
        end = System.nanoTime()
        println(end - start)

        println(L.getContentBit())

//        15785
//        386432
//        6565
//        12781

//        21161
//        47025185
//        7753
//        24584


    }
}