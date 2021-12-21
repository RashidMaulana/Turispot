package com.example.turispot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class KategoriBudaya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_budaya)
    }

    fun alam (view: View){
        val toAlam = Intent(this,KategoriAlam::class.java)
        startActivity(toAlam)
    }

    fun makanan (view: View){
        val toMakanan = Intent(this,KategoriMakanan::class.java)
        startActivity(toMakanan)
    }
    fun Sejarah (view: View){
        val toSejarah = Intent(this,KategoriSejarah::class.java)
        startActivity(toSejarah)
    }
}