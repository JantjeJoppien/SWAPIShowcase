package dev.joppien.swapishowcase.util

fun String?.safeToInt(): Int? {
    return this?.replace(",", "")
        ?.toIntOrNull()
}

fun String?.safeToLong(): Long? {
    return this?.replace(",", "")?.toLongOrNull()
}

fun String?.safeToDouble(): Double? {
    return this?.replace(",", "")?.toDoubleOrNull()
}

fun String?.toTrimmedStringList(): List<String> {
    return this?.split(',')
        ?.map { it.trim() }
        ?.filter { it.isNotEmpty() }
        ?: emptyList()
}