package com.example.turispot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class KategoriSejarah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kategori_sejarah)
    }

    fun alam (view: View){
        val toAlam = Intent(this,KategoriAlam::class.java)
        startActivity(toAlam)
    }

    fun makanan (view: View){
        val toSejarah = Intent(this,KategoriMakanan::class.java)
        startActivity(toSejarah)
    }
    fun Budaya (view: View){
        val toBudaya = Intent(this,KategoriBudaya::class.java)
        startActivity(toBudaya)
    }

    fun fBack(view: View) {
        val toBeranda = Intent(this,Beranda::class.java)
        startActivity(toBeranda)
    }
}