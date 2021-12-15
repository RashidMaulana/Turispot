package com.example.turispot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class BerandaOwner : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_owner)


        var namaOwner = intent.getStringExtra("namaOwner")
        var emailOwner = intent.getStringExtra("emailOwner")
        var birthOwner = intent.getStringExtra("birthOwner")
        var alamatOwner = intent.getStringExtra("alamatOwner")


        var textViewNama = findViewById<TextView>(R.id.NamaPemilikHome).apply {
            text = namaOwner
        }

        var textViewEmail = findViewById<TextView>(R.id.emailPemilikHome).apply {
            text = emailOwner
        }

        var textViewTanggalLahir = findViewById<TextView>(R.id.TanggalLahirPemilikHome).apply {
            text = birthOwner
        }

        var textViewAlamatPemilikHome = findViewById<TextView>(R.id.AlamatPemilikHome).apply {
            text = alamatOwner
        }
    }
}