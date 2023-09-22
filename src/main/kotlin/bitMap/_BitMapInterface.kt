package bitMap


operator fun <GET, I, O> BitMapInterface<GET, I, O>.minusAssign(index: GET) {
    this.removeAt(index)
}

operator fun <GET, I, O> BitMapInterface<GET, I, O>.minus(index: GET): BitMapInterface<GET, I, O> =
    this.copy().also { it -= index }

operator fun <GET, I, O> BitMapInterface<GET, I, O>.plusAssign(element: Boolean) {
    this.add(element)
}

operator fun <GET, I, O> BitMapInterface<GET, I, O>.plus(element: Boolean): BitMapInterface<GET, I, O> =
    this.copy().also { it += element }

operator fun <GET, I, O> BitMapInterface<GET, I, O>.get(index: GET): Boolean = this.get(index)

operator fun <GET, I, O> BitMapInterface<GET, I, O>.set(index: GET, element: Boolean): Boolean =
    this.set(index, element)

operator fun <GET, I, O> BitMapInterface<GET, I, O>.contains(index: GET): Boolean = this.contains(index)
operator fun <GET, I, O> BitMapInterface<GET, I, O>.invoke(bitMapInterfaceRun: BitMapInterface<GET, I, O>.() -> Unit) {
    this.bitMapInterfaceRun()
}
