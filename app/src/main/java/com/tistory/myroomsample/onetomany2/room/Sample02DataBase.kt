package com.tistory.myroomsample.onetomany2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FolderEntity::class, FileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Sample02DataBase : RoomDatabase() {

    abstract fun getSampleDao(): Sample02Dao

    companion object {

        private var INSTANCE: Sample02DataBase? = null

        fun getInstance(context: Context): Sample02DataBase {
            if (INSTANCE == null) {
                synchronized(RoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context, Sample02DataBase::class.java, "Sample02DataBase.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}