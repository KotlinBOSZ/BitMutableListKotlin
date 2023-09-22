package bitMap

import java.math.BigInteger

object BitPowLong : BitPowStorage<Long>(1,{it * 2})
object BitPowBigInteger : BitPowStorage<BigInteger>(BigInteger.valueOf(1),{it * BigInteger.valueOf(2)})

open class BitPowStorage<T>(startValue: T, private val createPowValue: (T) -> T) : BitPowInterface<Int, T> {
    @Volatile
    var array = mutableListOf(startValue)

    @Volatile
    var lock = Any()
    override fun pow(index: Int): T =
        when {
            0 > index -> throw IndexOutOfBoundsException()
            array.size > index -> array[index]
            array.size == index -> synchronized(lock) {
                array += createPowValue(array.last())
                array.last()
            }
            else -> synchronized(lock) {
                while (array.size != index+1){
                    array += createPowValue(array.last())
                }
                array.last()
            }
        }
}