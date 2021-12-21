package com.example.turispot

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class DetailWisata : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_wisata)

        var namawisata = intent.getStringExtra("tvNama")
        var iduser = intent.getIntExtra("iduser",0)
        var alamatwisata = intent.getStringExtra("tvAlamat")
        var hargawisata = intent.getStringExtra("tvHarga")
        var kategoriwisata = intent.getStringExtra("tvKategori")

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

        var txnama = findViewById<TextView>(R.id.txNama)
        var txkategori = findViewById<TextView>(R.id.txIsiKategori)
        var txalamat = findViewById<TextView>(R.id.txAlamat)
        var txcost = findViewById<TextView>(R.id.txCost)

        var etRating = findViewById<EditText>(R.id.etRating)
        var etUlasan = findViewById<EditText>(R.id.etUlasan)

        txnama.text = namawisata
        txkategori.text = kategoriwisata
        txalamat.text = alamatwisata
        txcost.text = hargawisata





        var btKirim = findViewById<Button>(R.id.btKirim)
        btKirim.setOnClickListener{
            var a: Int = findViewById<EditText>(R.id.etRating).text.toString().toInt()
            if(etRating.text.isEmpty() || etUlasan.text.isEmpty()){
                Toast.makeText(applicationContext, "Silahkan isi formulir dengan benar!", Toast.LENGTH_LONG).show()
            }
            else{

              if (a > 5){
                  Toast.makeText(applicationContext, "Rating melebihi ketentuan", Toast.LENGTH_LONG).show()
              }
                else{
                    var namawisatareview: String = txnama.text.toString()

                  var argsUser = listOf<String>(namawisatareview).toTypedArray()
                  var cursor : Cursor = reg.rawQuery("select * from wisata where nama = ?", argsUser)
                  if(cursor.moveToNext()){

                  var konten: String = etUlasan.text.toString().trim()
                  var idwisata = cursor.getInt(cursor.getColumnIndex("ID").toInt())


                  var cv = ContentValues()
                  cv.put("konten", konten)
                  cv.put("rating", a)
                  cv.put("tanggal", "20/12/2021")
                  cv.put("id_user", iduser)
                  cv.put("id_wisata",  idwisata)


                      reg.insert("review", null, cv)
                      Toast.makeText(getApplicationContext(),"Selamat review anda telah tersimpan!",Toast.LENGTH_SHORT).show()

                      val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                      val currentDate = sdf.format(Date())

                      var namaachievement = "Traveler Pemula"
                      var cvachievement = ContentValues()
                      cvachievement.put("user_id", iduser)
                      cvachievement.put("id_detail", 1)
                      cvachievement.put("tanggal",currentDate)

                      reg.insert("achievement", null, cvachievement)

                      db.updateUserExp(iduser)






                  } else{
                      Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show()
                  }

              }
            }
        }


        Log.d(TAG, iduser.toString())
    }
}