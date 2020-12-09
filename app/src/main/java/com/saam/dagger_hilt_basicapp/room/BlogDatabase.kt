package com.saam.dagger_hilt_basicapp.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.saam.dagger_hilt_basicapp.room.BlogCacheEntity
import com.saam.dagger_hilt_basicapp.room.BlogDao

@Database(entities = [BlogCacheEntity::class ], version = 1)
abstract class BlogDatabase: RoomDatabase() {

    abstract fun blogDao(): BlogDao

    companion object{
        val DATABASE_NAME: String = "blog_db"
    }
}