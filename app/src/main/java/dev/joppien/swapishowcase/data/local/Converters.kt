package dev.joppien.swapishowcase.data.local

import androidx.room.TypeConverter
import timber.log.Timber
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Converters {
    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE

    @TypeConverter
    fun fromLocalDate(date: LocalDate?): String? {
        return date?.format(formatter)
    }

    @TypeConverter
    fun toLocalDate(value: String?): LocalDate? {
        return value?.let {
            try {
                LocalDate.parse(it, formatter)
            } catch (e: Exception) {
                Timber.e(e, "Error parsing String to Date")
                null
            }
        }
    }
}