package com.example.turispot

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
=======
import android.widget.TextView
>>>>>>> 245d65006fb3ce1b217d10bd300734d2dee48d20

class BerandaOwner : AppCompatActivity() {


    //imagebutton

//     var buttonProfile = findViewById<ImageButton>(R.id.imageButton)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_beranda_owner)

<<<<<<< HEAD
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
=======
>>>>>>> 245d65006fb3ce1b217d10bd300734d2dee48d20

        var namaOwner = intent.getStringExtra("namaOwner")
        var emailOwner = intent.getStringExtra("emailOwner")
        var birthOwner = intent.getStringExtra("birthOwner")
        var alamatOwner = intent.getStringExtra("alamatOwner")
<<<<<<< HEAD
        var IDOwner = intent.getIntExtra("IDOwner", 0)
        var usernameOwner = intent.getStringExtra("usernameOwner")
=======

>>>>>>> 245d65006fb3ce1b217d10bd300734d2dee48d20

        var textViewNama = findViewById<TextView>(R.id.NamaPemilikHome).apply {
            text = namaOwner
        }

<<<<<<< HEAD


        var argsUser = listOf<String>(IDOwner.toString()).toTypedArray()
        var cursorOwner: Cursor =
            reg.rawQuery(
                "SELECT w.nama AS namawisata, w.alamat AS alamat, k.nama AS kategori FROM wisata w INNER JOIN kategori k ON w.kategori = k.ID WHERE w.id_pemilik = ?",
                argsUser
            )

        if (cursorOwner.moveToNext()) {
            try {
                var alamatOwnerdb =
                    cursorOwner.getString(cursorOwner.getColumnIndex("alamat").toInt())
                var kategoriOwnerdb =
                    cursorOwner.getString(cursorOwner.getColumnIndex("kategori").toInt())
                var namaOwnerdb =
                    cursorOwner.getString(cursorOwner.getColumnIndex("namawisata").toInt())

                var etNama = findViewById<TextView>(R.id.textView4).setText(namaOwnerdb)
                var etAlamat = findViewById<TextView>(R.id.textView10).setText(alamatOwnerdb)
                var etKategori = findViewById<TextView>(R.id.textView8).setText(kategoriOwnerdb)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }


        fun pindahProfileOwner(view: View) {

            var namaOwner = intent.getStringExtra("namaOwner")
            var IDOwner = intent.getIntExtra("IDOwner", 0)
            var usernameOwner = intent.getStringExtra("usernameOwner")
            val intKeProfile = Intent(this, ProfileOwner2::class.java).apply {
                putExtra("usernameOwner", usernameOwner)
                putExtra("namaOwner", namaOwner)
                putExtra("IDOwner", IDOwner)
            }
            startActivity(intKeProfile)
        }

        fun reviewOwner(view: View) {
            var idowner = intent.getIntExtra("IDOwner", 0)
            var db = DatabaseHelper(this, "turispot.db", 1)
            var reg = db.readableDatabase
            var argsUser = listOf<String>(idowner.toString()).toTypedArray()
            var cursorOwner2: Cursor =
                reg.rawQuery(
                    "SELECT * FROM wisata WHERE id_pemilik = ? ",
                    argsUser
                )
            if (cursorOwner2.moveToNext()){
                var WisataOwnerdb = cursorOwner2.getInt(cursorOwner2.getColumnIndex("ID").toInt())
                val intentKeReview = Intent(this, ReviewOwner2::class.java).apply{
                    putExtra("IDOwner", idowner)
                    putExtra("IDWisata", WisataOwnerdb)
                }
                startActivity(intentKeReview)
            }
            else{
                Toast.makeText(getApplicationContext(),"Belum Ada Wisata atau belum ada review!",Toast.LENGTH_SHORT).show()
            }

=======
        var textViewEmail = findViewById<TextView>(R.id.emailPemilikHome).apply {
            text = emailOwner
        }

        var textViewTanggalLahir = findViewById<TextView>(R.id.TanggalLahirPemilikHome).apply {
            text = birthOwner
        }

        var textViewAlamatPemilikHome = findViewById<TextView>(R.id.AlamatPemilikHome).apply {
            text = alamatOwner
>>>>>>> 245d65006fb3ce1b217d10bd300734d2dee48d20
        }
    }
