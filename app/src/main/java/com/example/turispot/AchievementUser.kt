package com.example.turispot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class AchievementUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievement_user)

        var IDUser = intent.getStringExtra("ID")
        var namaUser = intent.getStringExtra("namaUser")

        var textViewNama = findViewById<TextView>(R.id.tvNama).apply {
            text = namaUser
        }
    }
}