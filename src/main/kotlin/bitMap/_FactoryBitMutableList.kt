package bitMap

import java.math.BigInteger

fun FactoryBitMutableList.Companion.longImpl(content: Long = 0L, size: Int = 0) =
    object : FactoryBitMutableList<Long, Long>(
        content,
        Long::and,
        Long::shl,
        Long::shr,
        Long::minus,
        Long::plus,
        BitPowLong::pow,
        { it },
        Int::toLong,
        size,
        62,
        0L
    ) {

    }

fun FactoryBitMutableList.Companion.bigIntegerImpl(content: BigInteger = BigInteger.valueOf(0), size: Int = 0) =
    object : FactoryBitMutableList<Long, BigInteger>(
        content,
        BigInteger::and,
        BigInteger::shl,
        BigInteger::shr,
        BigInteger::minus,
        BigInteger::plus,
        BitPowBigInteger::pow,
        { BigInteger.valueOf(it) },
        Int::toLong,
        size,
        getInstance0 = BigInteger.valueOf(0)
    ) {

    }


