package bitMap

interface BitMapInterface<in GET, in I, out O> {
    fun copy(): BitMapInterface<GET, I, O>
    fun getContentBit(): O
    fun getLimit(): Int
//    fun add(element: Boolean): Boolean
    fun setSizeAt(index: GET)

    fun addAt(index: GET): Boolean

    fun get(index: GET): Boolean
    fun removeAt(index: GET): Boolean
    fun set(index: GET, element: Boolean): Boolean
    fun contains(element: Boolean): Boolean

    companion object {
        const val UNLIMITED = -1
    }
}

//sealed interface LimitType {
//    object NoLimit : LimitType
//    data class Limit(val limit: Long) : LimitType {
//        fun check(index: Long) {
//            if (index > limit) throw LimitException(limit, index)
//        }
//    }
//
//    fun <T : Number> checkLimit(index: T): T {
//        when (this) {
//            NoLimit -> return index
//            is Limit -> check(index.toLong())
//        }
//        return index;
//    }
//}
//
//class LimitException(limit: Long, index: Long) : RuntimeException("limit: ${limit} < index: ${index}");