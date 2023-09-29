package bitMap

import java.math.BigInteger

val BitPow.Companion.longImpl get() = object : BitPow<Long>(1, { it * 2 }) {}
val BitPow.Companion.bigIntegerImpl get() = object : BitPow<BigInteger>(BigInteger.valueOf(1), { it * BigInteger.valueOf(2) }) {}

