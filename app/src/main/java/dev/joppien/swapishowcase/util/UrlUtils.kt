package dev.joppien.swapishowcase.util

fun extractIdFromUrl(url: String?): Int? {
    return url?.trimEnd('/')?.substringAfterLast('/')?.toIntOrNull()
}

fun extractIdsFromUrls(urlsString: String?): List<Int> {
    return urlsString?.split(",")
        ?.mapNotNull { url -> url.trim().takeIf { it.isNotEmpty() } }
        ?.mapNotNull { extractIdFromUrl(it) }
        ?: emptyList()
}