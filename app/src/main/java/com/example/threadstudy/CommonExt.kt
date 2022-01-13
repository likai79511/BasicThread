fun Any.i(msg: String) {
    println("---$msg---")
}

fun <T> printArray(data: Array<T>?, method: ((bean: T) -> String)? = null):String {
    if (data ==null || data.isEmpty())
        return " Empty "
    var sb = StringBuffer()
    sb.append("[")
    data.forEach {
        sb.append(method?.invoke(it) ?: it.toString())
    }
    sb.append("]")
    return sb.toString()
}