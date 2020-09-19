package com.tistory.myroomsample.onetomany.room

import androidx.room.*

@Dao
interface Sample01Dao {

    /**
     * Folder
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFolder(folder: FolderEntity)

    @Delete
    fun deleteFolder(folder: FolderEntity)

    @Query("SELECT * FROM folders ORDER BY folderId DESC") //ASC
    fun getFolders(): List<FolderEntity>

    @Query("SELECT * FROM folders WHERE name = :name")
    fun getFolderByName(name: String): FolderEntity

    @Query("DELETE FROM folders WHERE name = :name")
    fun deleteFolderByName(name: String)

    @Query("DELETE FROM folders")
    fun clearFolders()


    /**
     * File
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFile(file: FileEntity)

    @Delete
    fun deleteFile(file: FileEntity)

    @Query("SELECT * FROM files")
    fun getFiles(): List<FileEntity>

    @Query("DELETE FROM files WHERE name = :name")
    fun deleteFilerByName(name: String)

    @Query("DELETE FROM files")
    fun clearFiles()


    /**
     * Folder And File
     */
    @Transaction
    @Query("SELECT * FROM folders ORDER BY folderId DESC") //ASC
    fun getFolderAndFilesData(): List<FolderAndFiles>
}