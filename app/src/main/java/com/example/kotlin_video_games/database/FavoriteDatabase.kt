package com.example.kotlin_video_games.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDAO

    companion object {
        private var instance: FavoriteDatabase? = null

        fun getFavoriteDatabase(context: Context): FavoriteDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    FavoriteDatabase::class.java,
                    "favorites.db"
                ).allowMainThreadQueries().build()
            }
            return instance
        }
    }
}