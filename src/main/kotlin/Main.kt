import bitMap.UByteArrayDelegation

fun main(args: Array<String>) {
//    println(UByte.MAX_VALUE - UByte.MIN_VALUE)
//    println(Byte.MAX_VALUE - Byte.MIN_VALUE)

//    var b : UByte
//    b = 255u
//
//    println(b+5u)
//    println(b)

//    println(1400u.toUInt().toString(radix = 36))
//    println(Int.MAX_VALUE.countOneBits())
//    println(ULong.MAX_VALUE)
//    println(ULong.MAX_VALUE.takeHighestOneBit())
//    println(ULong.MAX_VALUE.takeLowestOneBit())
//    println(ULong.MAX_VALUE.countOneBits())
//    println(ULong.MAX_VALUE.countLeadingZeroBits())
//    println(4.countTrailingZeroBits())
//    println(129.toUByte().countOneBits())


//    byteArrayOf().toUByteArray()
//    println(8U.toUInt() shr 1)
//    println(8U.toUByte() shr 1)

    val u = UByteArrayDelegation()
    var a by u
    println(a.toList())
    u.add(200U)
    println(a.toList())
    u.add(20U)
    println(a.toList())
    u.add(200U)
    println(a.toList())
    u.add(200U)
    println(a.toList())







}
//
//class Tak(var content: Int) {
//    operator fun getValue(thisRef: Any?, property: KProperty<*>): Int = content
//
//
//    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
//        content = value
//    }
//}
