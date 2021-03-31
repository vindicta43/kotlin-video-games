package com.example.kotlin_video_games.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "gameID")
    var gameID: Int,
)