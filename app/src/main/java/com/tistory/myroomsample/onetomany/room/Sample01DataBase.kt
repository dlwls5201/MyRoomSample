package com.tistory.myroomsample.onetomany.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [FolderEntity::class, FileEntity::class],
    version = 1,
    exportSchema = false
)
abstract class Sample01DataBase : RoomDatabase() {

    abstract fun getSampleDao(): Sample01Dao

    companion object {

        private var INSTANCE: Sample01DataBase? = null

        fun getInstance(context: Context): Sample01DataBase {
            if (INSTANCE == null) {
                synchronized(RoomDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context, Sample01DataBase::class.java, "Sample01DataBase.db"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}