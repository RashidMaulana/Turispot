package com.example.turispot

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class ProfileOwner2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_owner2)

        //mendeklarasi DB dan mengimport db
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
        var namaOwner = intent.getStringExtra("namaOwner")
        var usernameOwner = intent.getStringExtra("usernameOwner")

        var etNama = findViewById<EditText>(R.id.EditNama)
        var etTanggal = findViewById<EditText>(R.id.EditUsername)
        var etAlamat = findViewById<EditText>(R.id.EditAlamat)
        var etEmail = findViewById<EditText>(R.id.EditEmail)
        var bSimpan = findViewById<Button>(R.id.button3)




        //ngambil data owner dari database
        var argsUser = listOf<String>(IDOwner.toString()).toTypedArray()
        var cursorOwner : Cursor = reg.rawQuery("SELECT * FROM pemilik WHERE id = ?", argsUser)

        if(cursorOwner.moveToNext()) {
            try{
                var alamatOwnerdb = cursorOwner.getString(cursorOwner.getColumnIndex("alamat").toInt())
                var emailOwnerdb = cursorOwner.getString(cursorOwner.getColumnIndex("email").toInt())
                var birthOwnerdb = cursorOwner.getString(cursorOwner.getColumnIndex("tanggal_lahir").toInt())
                var namaOwnerdb = cursorOwner.getString(cursorOwner.getColumnIndex("nama").toInt())
                var fotoOwnerdb = cursorOwner.getString(cursorOwner.getColumnIndex("foto").toInt())

                var etNama = findViewById<EditText>(R.id.EditNama).setText(namaOwnerdb)
                var etTanggal = findViewById<EditText>(R.id.EditTanggal).setText(birthOwnerdb)
                var etAlamat = findViewById<EditText>(R.id.EditAlamat).setText(alamatOwnerdb)
                var etEmail = findViewById<EditText>(R.id.EditEmail).setText(emailOwnerdb)
            }catch (e : Exception){
                e.printStackTrace()
            }

        }else{
            Toast.makeText(getApplicationContext(),"System gagal mengambil dari database", Toast.LENGTH_SHORT).show()
        }

        //nama text view usernama Owner
        var textusername = findViewById<TextView>(R.id.textView18).apply {
            text = namaOwner
        }

        var button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener{
//            var argsEdit = listOf<String>(editnama,edittanggal,editemail,editalamat,IDOwner.toString()).toTypedArray()
//            var cursor2 : Cursor = reg.rawQuery("UPDATE pemilik SET nama = ?, tanggal_lahir = ?, email = ?, alamat = ? WHERE ID = ?", argsEdit)
            var editnama = etNama.text.toString()
            var edittanggal = etTanggal.text.toString()
            var editemail = etEmail.text.toString()
            var editalamat = etAlamat.text.toString()

            db.updateName(editnama,edittanggal,editemail,editalamat,IDOwner)

        }






    }
}