package com.tistory.myroomsample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tistory.myroomsample.onetomany.Sample01Activity
import com.tistory.myroomsample.onetomany2.Sample02Activity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnOneToMany.setOnClickListener {
            startActivity(
                Intent(this, Sample01Activity::class.java)
            )
        }

        btnManyToMany.setOnClickListener {
            startActivity(
                Intent(this, Sample02Activity::class.java)
            )
        }
    }
}