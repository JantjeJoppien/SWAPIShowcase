package dev.joppien.swapishowcase.util

fun extractIdFromUrl(url: String?): Int? {
    return url?.trimEnd('/')?.substringAfterLast('/')?.toIntOrNull()
}