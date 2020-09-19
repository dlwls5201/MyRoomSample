package com.tistory.myroomsample.onetomany.room

import androidx.room.*

@Entity(tableName = "folders")
data class FolderEntity(
    @PrimaryKey(autoGenerate = true)
    val folderId: Int = 0,
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
        )
    ]
)
data class FileEntity(
    @PrimaryKey(autoGenerate = true)
    val fileId: Int = 0,
    val parentFolderId: Int,
    val name: String
)

data class FolderAndFiles(
    @Embedded val folder: FolderEntity,

    @Relation(
        parentColumn = "folderId",
        entityColumn = "parentFolderId"
    )
    val files: List<FileEntity>
)

