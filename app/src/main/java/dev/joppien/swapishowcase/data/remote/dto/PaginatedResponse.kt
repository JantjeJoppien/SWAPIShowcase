package dev.joppien.swapishowcase.data.remote.dto

import com.squareup.moshi.Json

data class PaginatedResponse<T>(
    @param:Json(name = "count")
    val count: Int,

    @param:Json(name = "next")
    val next: String?,

    @param:Json(name = "previous")
    val previous: String?,

    @param:Json(name = "results")
    val results: List<T>
)