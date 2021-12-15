package com.example.turispot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class Beranda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)



        var namaUser = intent.getStringExtra("namaUser")
        var emailUser = intent.getStringExtra("emailUser")
        var expUser = intent.getStringExtra("EXPUser").toString()
        var levelUser = intent.getStringExtra("levelUser").toString()

        var textViewNama = findViewById<TextView>(R.id.NamaUserHome).apply {
            text = namaUser
        }

        var textViewEmail = findViewById<TextView>(R.id.EmailUserHome).apply {
            text = emailUser
        }

        var textViewLevel = findViewById<TextView>(R.id.LevelUserHome).apply {
            text = levelUser
        }

        var textViewExp = findViewById<TextView>(R.id.EXPUserHome).apply {
            text = expUser
        }


    }
}