package com.mirfanrafif.kicksfilm.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tvshowentities")
data class TvShowEntity (
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tvShowId")
    var id: Int,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "year")
    var year: Int,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "rating")
    var rating: Double,

    @ColumnInfo(name = "category")
    var category: String?,

    @ColumnInfo(name = "photoUrl")
    var photo: String,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
): Parcelable