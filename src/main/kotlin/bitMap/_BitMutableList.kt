package bitMap

import java.math.BigInteger

fun BitMutableList.Companion.longImpl(content: Long = 0L, size: Int = 0) =
    object : BitMutableList<Long, Long>(
        content,
        Long::and,
        Long::shl,
        Long::shr,
        Long::minus,
        Long::plus,
        BitPow.longImpl::pow,
        { it },
        Int::toLong,
        size,
        62,
        0L
    ) {

    }

fun BitMutableList.Companion.bigIntegerImpl(content: BigInteger = BigInteger.valueOf(0), size: Int = 0) =
    object : BitMutableList<Long, BigInteger>(
        content,
        BigInteger::and,
        BigInteger::shl,
        BigInteger::shr,
        BigInteger::minus,
        BigInteger::plus,
        BitPow.bigIntegerImpl::pow,
        { BigInteger.valueOf(it) },
        Int::toLong,
        size,
        getInstance0 = BigInteger.valueOf(0)
    ) {

    }

//fun BitMutableList.Companion.ubyteArrayDelegationImpl(content: UByteArrayDelegation = UByteArrayDelegation(), size: Int = 0) =
//    object : BitMutableList<UByteArrayDelegation, UByteArrayDelegation>(
//        content = content,
//        and = ,
//        shl = ,
//        shr = ,
//        minusOperator = ,
//        plusOperator = ,
//        powInstance = ,
//        getInstance = {  },
//        toInput = ,
//        size = size,
//        getInstance0 = UByteArrayDelegation()
//    ) {
//
//    }


