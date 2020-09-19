package com.tistory.myroomsample.onetomany2.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "folders",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = arrayOf("folderId"),
            childColumns = arrayOf("parentId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val folderId: Int = 0,
    val parentId: Int? = null,
    val name: String
)

@Entity(
    tableName = "files",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = arrayOf("folderId"),
            childColumns = arrayOf("parentFolderId"),
            onDelete = ForeignKey.CASCADE
        )]
)
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val fileId: Int = 0,
    val parentFolderId: Int,
    val name: String
)