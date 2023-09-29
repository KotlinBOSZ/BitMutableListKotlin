package bitMap

import kotlin.reflect.KProperty

@OptIn(ExperimentalUnsignedTypes::class)
class UByteArrayDelegation(var content: UByteArray = ubyteArrayOf(0U)) {

    companion object{

    }
    operator fun getValue(thisRef: Any?, property: KProperty<*>): UByteArray = content
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: UByteArray) {
        content = value
    }

    fun add(array: UByteArray,ubyte: UByte): UByteArray{
        var of = array.copyOf()
        val uInt: UInt = of.last() + ubyte
        if (uInt <= 255u) {
            of[of.size - 1] = uInt.toUByte()

        } else {
            of = of.copyOf(array.size + 1)
            of[of.size - 2] = 255u
            of[of.size - 1] = (uInt+1u).toUByte()

        }
        return of
    }

    fun add(ubyte: UByte){
        content = add(content,ubyte)
    }



}


