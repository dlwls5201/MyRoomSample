package com.tistory.myroomsample.onetomany

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tistory.myroomsample.R
import com.tistory.myroomsample.onetomany.room.FileEntity
import com.tistory.myroomsample.onetomany.room.FolderEntity
import com.tistory.myroomsample.onetomany.room.Sample01DataBase
import kotlinx.android.synthetic.main.activity_sample01.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Sample01Activity : AppCompatActivity() {

    companion object {
        private const val TAG = "BlackJin"
    }

    private val sample01DataBase by lazy {
        Sample01DataBase.getInstance(this).getSampleDao()
    }

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample01)

        initFolder()
        initFile()
        initFolderAndFiles()
    }

    private fun initFolderAndFiles() {
        btnGetFolderAndFiles.setOnClickListener {
            executorService.execute {
                val items = sample01DataBase.getFolderAndFilesData()
                Log.d(TAG, "items : $items")
            }
        }
    }


    private var fileNameCnt = 0

    private fun initFile() {
        btnInsertFile.setOnClickListener {
            executorService.execute {
                fileNameCnt++
                val parentFile = sample01DataBase.getFolderByName("folderNameCnt${folderNameCnt}")
                Log.d(TAG, "insert parentFile : $parentFile")

                val newFile = FileEntity(
                    name = "fileNameCnt${fileNameCnt}",
                    parentFolderId = parentFile.folderId
                )

                sample01DataBase.insertFile(newFile)
                Log.d(TAG, "insert file : $newFile")
            }
        }

        btnDeleteFile.setOnClickListener {
            executorService.execute {
                val name = "fileNameCnt${fileNameCnt}"
                sample01DataBase.deleteFilerByName(name)
                Log.d(TAG, "delete file : $name")
            }
        }

        btnGetFiles.setOnClickListener {
            executorService.execute {
                val files = sample01DataBase.getFiles()
                Log.d(TAG, "files : $files")
            }
        }

        btnClearFolders.setOnClickListener {
            executorService.execute {
                sample01DataBase.clearFiles()
                Log.d(TAG, "clear files")
            }
        }
    }


    private var folderNameCnt = 0

    private fun initFolder() {
        btnInsertFolder.setOnClickListener {
            executorService.execute {
                folderNameCnt++
                val newFolder = FolderEntity(name = "folderNameCnt${folderNameCnt}")
                sample01DataBase.insertFolder(newFolder)
                Log.d(TAG, "insert folder : $newFolder")
            }
        }

        btnDeleteFolder.setOnClickListener {
            executorService.execute {
                val name = "folderNameCnt${folderNameCnt}"
                sample01DataBase.deleteFolderByName(name)
                Log.d(TAG, "delete folder : $name")
            }
        }

        btnGetFolders.setOnClickListener {
            executorService.execute {
                val folders = sample01DataBase.getFolders()
                Log.d(TAG, "folders : $folders")
            }
        }

        btnClearFolders.setOnClickListener {
            executorService.execute {
                sample01DataBase.clearFolders()
                Log.d(TAG, "clear folders")
            }
        }
    }
}