package com.example.turispot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ProfileOwner : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_owner)

        var IDOwner = intent.getStringExtra("IDOwner")
        var namaOwner = intent.getStringExtra("namaOwner")

        //nama text view usernama Owner
        var textusername = findViewById<TextView>(R.id.textView18).apply {
            text = namaOwner
        }
    }

}