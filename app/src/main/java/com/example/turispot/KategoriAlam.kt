package com.example.turispot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class KategoriAlam : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_alam)
    }
    fun makanan (view: View){
        val toMakanan = Intent(this,KategoriMakanan::class.java)
        startActivity(toMakanan)
    }

    fun Sejarah (view: View){
        val toSejarah = Intent(this,KategoriSejarah::class.java)
        startActivity(toSejarah)
    }
    fun Budaya (view: View){
        val toBudaya = Intent(this,KategoriBudaya::class.java)
        startActivity(toBudaya)
    }

}