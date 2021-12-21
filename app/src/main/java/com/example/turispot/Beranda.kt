package com.example.turispot

import android.content.ContentValues
import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text
import java.lang.Exception
=======
import android.widget.TextView
import org.w3c.dom.Text
>>>>>>> 245d65006fb3ce1b217d10bd300734d2dee48d20

class Beranda : AppCompatActivity() {

    private var layoutmanager : RecyclerView.LayoutManager? = null
    private var adapter : RecyclerView.Adapter<AdapterWisata.MyViewHolder>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda)



<<<<<<< HEAD
        var iduser = intent.getIntExtra("ID",0)
        var namaUser = intent.getStringExtra("namaUser")
        var emailUser = intent.getStringExtra("emailUser")
        var expUser = intent.getStringExtra("EXPUser").toString()


        var db = DatabaseHelper(this, "turispot.db", 1)
        var reg = db.writableDatabase
        try {
            db.CheckDb();
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            db.OpenDatabase()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        displaydata(iduser)
//        var argsUser = listOf<String>(IDWisata.toString()).toTypedArray()
    }

    fun fpindahmakan(view: View) {
        val intKeMakan = Intent(this, KategoriMakanan::class.java)
        startActivity(intKeMakan)


    }
    fun fpindahbudaya(view: View) {
        val intKeBudaya = Intent(this, KategoriBudaya::class.java)
        startActivity(intKeBudaya)


    }

    fun fpindahsejarah(view: View) {
        val intKeSejarah = Intent(this, KategoriSejarah::class.java)
        startActivity(intKeSejarah)


    }
    fun fpindahalam(view: View) {
        val intKeAlam = Intent(this, KategoriAlam::class.java)
        startActivity(intKeAlam)


    }

    fun PindahProfile (view : View){
        var IDUser = intent.getIntExtra("ID",0)
        var namaUser = intent.getStringExtra("namaUser")
        var emailUser = intent.getStringExtra("emailUser")
        var expUser = intent.getIntExtra("EXPUser",0)
        var levelUser = intent.getIntExtra("levelUser",0)

        val intent = Intent(this, ProfileUser::class.java).apply {
            putExtra("ID", IDUser)
            putExtra("EXPUser", expUser)
            putExtra("levelUser", levelUser)
            putExtra("emailUser", emailUser)
            putExtra("namaUser", namaUser)
        }
        startActivity(intent)
    }


    fun displaydata(userid: Int){
        var namawisata = ArrayList<String>()
        var hargawisata = ArrayList<String>()
        var alamatwisata = ArrayList<String>()
        var kategoriwisata = ArrayList<String>()

        var userid = userid

        var db = DatabaseHelper(this, "turispot.db", 1)
        var reg = db.writableDatabase
        var cursor : Cursor = reg.rawQuery(
            "SELECT w.nama AS wisata, w.harga AS harga, w.alamat AS alamat, k.nama AS kategori  FROM wisata w INNER JOIN kategori k ON w.kategori = k.ID ",null
        )
        if(cursor.count == 0){
            Toast.makeText(getApplicationContext(),"Data Wisata tidak ada", Toast.LENGTH_SHORT).show()
        }
        else{
            while (cursor.moveToNext()){

                namawisata.add(cursor.getString(cursor.getColumnIndex("wisata").toInt()))
                hargawisata.add(cursor.getString(cursor.getColumnIndex("harga").toInt()))
                alamatwisata.add(cursor.getString(cursor.getColumnIndex("alamat").toInt()))
                kategoriwisata.add(cursor.getString(cursor.getColumnIndex("kategori").toInt()))
                Log.d(ContentValues.TAG,"Cursor " + namawisata + hargawisata + alamatwisata + kategoriwisata)
            }
            Toast.makeText(getApplicationContext(),"Data review ada", Toast.LENGTH_SHORT).show()
        }
        var customadapter = AdapterWisata(this,namawisata, alamatwisata, hargawisata,kategoriwisata,userid)

        var rvReview = findViewById<RecyclerView>(R.id.rvWisata)
        layoutmanager = LinearLayoutManager(this)
        rvReview.layoutManager = layoutmanager

        adapter = customadapter

        rvReview.adapter = adapter
=======
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

>>>>>>> 245d65006fb3ce1b217d10bd300734d2dee48d20

    }
}