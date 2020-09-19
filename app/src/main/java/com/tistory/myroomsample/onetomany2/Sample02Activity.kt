package com.tistory.myroomsample.onetomany2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tistory.myroomsample.R
import com.tistory.myroomsample.onetomany2.room.FileEntity
import com.tistory.myroomsample.onetomany2.room.FolderEntity
import com.tistory.myroomsample.onetomany2.room.Sample02DataBase
import kotlinx.android.synthetic.main.activity_sample02.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Sample02Activity : AppCompatActivity() {

    companion object {
        private const val TAG = "BlackJin"
    }

    private val sample02DataBase by lazy {
        Sample02DataBase.getInstance(this).getSampleDao()
    }

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample02)

        initFolder()
        initFile()
    }

    private var fileNameCnt = 0

    private fun initFile() {
        btnInsertFile.setOnClickListener {
            executorService.execute {
                fileNameCnt++
                val parentFile = sample02DataBase.getFolderByName("folderNameCnt${folderNameCnt}")
                Log.d(TAG, "insert parentFile : $parentFile")

                val newFile = FileEntity(
                    name = "fileNameCnt${fileNameCnt}",
                    parentFolderId = parentFile.folderId
                )

                sample02DataBase.insertFile(newFile)
                Log.d(TAG, "insert file : $newFile")
            }
        }

        btnDeleteFile.setOnClickListener {
            executorService.execute {
                val name = "fileNameCnt${fileNameCnt}"
                sample02DataBase.deleteFilerByName(name)
                Log.d(TAG, "delete file : $name")
            }
        }

        btnGetFiles.setOnClickListener {
            executorService.execute {
                val files = sample02DataBase.getFiles()
                Log.d(TAG, "files : $files")
            }
        }

        btnClearFolders.setOnClickListener {
            executorService.execute {
                sample02DataBase.clearFiles()
                Log.d(TAG, "clear files")
            }
        }
    }


    private var parentChildNameCnt = 100

    private var folderNameCnt = 0

    private fun initFolder() {
        btnInsertFolder.setOnClickListener {
            executorService.execute {
                folderNameCnt++
                val newFolder = FolderEntity(name = "folderNameCnt${folderNameCnt}")
                sample02DataBase.insertFolder(newFolder)
                Log.d(TAG, "insert folder : $newFolder")
            }
        }

        btnInsertChildFolder.setOnClickListener {
            executorService.execute {
                val parentFile = sample02DataBase.getFolderByName("folderNameCnt${folderNameCnt}")
                Log.d(TAG, "insert parentFile : $parentFile")

                parentChildNameCnt++
                val newFolder = FolderEntity(
                    name = "parentChildNameCnt${parentChildNameCnt}",
                    parentId = parentFile.folderId
                )
                sample02DataBase.insertFolder(newFolder)
                Log.d(TAG, "insert child folder : $newFolder")
            }
        }

        btnDeleteFolder.setOnClickListener {
            executorService.execute {
                val name = "folderNameCnt${folderNameCnt}"
                sample02DataBase.deleteFolderByName(name)
                Log.d(TAG, "delete folder : $name")
            }
        }

        btnGetFolders.setOnClickListener {
            executorService.execute {
                val folders = sample02DataBase.getFolders()
                Log.d(TAG, "folders : $folders")
            }
        }

        btnClearFolders.setOnClickListener {
            executorService.execute {
                sample02DataBase.clearFolders()
                Log.d(TAG, "clear folders")
            }
        }
    }
}