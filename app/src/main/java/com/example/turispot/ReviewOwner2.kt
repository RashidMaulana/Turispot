package com.example.turispot

import android.content.ContentValues.TAG
import android.database.Cursor
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.lang.Exception

class ReviewOwner2 : AppCompatActivity() {

    var namauser = ArrayList<String>()
    var ratinguser = ArrayList<Int>()
    var kontenuser = ArrayList<String>()
    var tanggalreview = ArrayList<String>()

    private var layoutmanager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<CustomAdapter.MyViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review_owner2)

        var namauser = ArrayList<String>()
        var ratinguser = ArrayList<Int>()
        var kontenuser = ArrayList<String>()
        var tanggalreview = ArrayList<String>()

        var db = DatabaseHelper(this, "turispot.db", 1)
        var reg = db.writableDatabase
        try{
            db.CheckDb();
        }catch (e : Exception){
            e.printStackTrace()
        }

        try{
            db.OpenDatabase()
        }catch (e : Exception){
            e.printStackTrace()
        }

        var IDOwner = intent.getIntExtra("IDOwner",0)
        var IDWisata = intent.getIntExtra("IDWisata", 0)

        displaydata()



    }

    fun displaydata (){

        var namauser = ArrayList<String>()
        var ratinguser = ArrayList<Int>()
        var kontenuser = ArrayList<String>()
        var tanggalreview = ArrayList<String>()

        var IDWisata = intent.getIntExtra("IDWisata", 0)
        var db = DatabaseHelper(this, "turispot.db", 1)
        var argsUser = listOf<String>(IDWisata.toString()).toTypedArray()
        var reg = db.writableDatabase
        var cursor : Cursor = reg.rawQuery(
            "SELECT u.nama AS username, r.rating AS rating, r.konten AS konten, r.tanggal AS tanggal  FROM review r INNER JOIN user u ON r.id_user = u.ID WHERE r.id_wisata = ? ",
            argsUser
        )
        if(cursor.count == 0){
            Toast.makeText(getApplicationContext(),"Data review tidak ada", Toast.LENGTH_SHORT).show()
        }
        else{
            while (cursor.moveToNext()){

                namauser.add(cursor.getString(cursor.getColumnIndex("username").toInt()))
                ratinguser.add(cursor.getInt(cursor.getColumnIndex("rating").toInt()))
                kontenuser.add(cursor.getString(cursor.getColumnIndex("tanggal").toInt()))
                tanggalreview.add(cursor.getString(cursor.getColumnIndex("konten").toInt()))
                Log.d(TAG,"Cursor " + namauser + ratinguser + kontenuser + tanggalreview)
            }
            Toast.makeText(getApplicationContext(),"Data review ada", Toast.LENGTH_SHORT).show()
        }
        var customadapter = CustomAdapter(this,namauser, ratinguser, kontenuser,tanggalreview)

        var rvReview = findViewById<RecyclerView>(R.id.rvReview)
        layoutmanager = LinearLayoutManager(this)
        rvReview.layoutManager = layoutmanager

        adapter = customadapter

        rvReview.adapter = adapter
    }




}