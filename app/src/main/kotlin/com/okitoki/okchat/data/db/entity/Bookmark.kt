package com.okitoki.okchat.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.okitoki.okchat.data.db.converter.DateConverter
import com.okitoki.okchat.data.net.domain.Repository
import java.util.*

/**
 * @author ridsync
 */
@Entity(tableName = "Bookmark")
@TypeConverters(DateConverter::class)
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "stargazers_count") val stargazersCount: Int,
    @ColumnInfo(name = "created") val created: Date
) {
    companion object {
        fun to(repository: Repository): Bookmark {
            return Bookmark(
                name = repository.name,
                description = repository.description,
                language = repository.language,
                stargazersCount = repository.stargazersCount,
                created = Date()
            )
        }
    }
}