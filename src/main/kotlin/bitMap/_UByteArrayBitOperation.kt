package bitMap

infix fun UByte.shl(bitCount: Int): UByte {
    val bits = ubyteArrayOf(1u, 2u, 4u, 8u, 16u, 32u, 64u, 128u)
    return bits.map { this.and(it) == it }.toBooleanArray().run { takeLast(bitCount) + dropLast(bitCount) }
        .mapIndexed { index, b -> if (b) bits[index] else 0U }.sum().toUByte()
}

infix fun UByte.shr(bitCount: Int): UByte {
    val bits = ubyteArrayOf(1u, 2u, 4u, 8u, 16u, 32u, 64u, 128u)
    return bits.map { this.and(it) == it }.toBooleanArray().run { drop(bitCount) + take(bitCount) }
        .mapIndexed { index, b -> if (b) bits[index] else 0U }.sum().toUByte()
}