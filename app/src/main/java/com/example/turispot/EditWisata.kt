package com.example.turispot

import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.lang.Exception

class EditWisata : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_wisata)

        var idowner = intent.getIntExtra("IDOwner",0)
        var idwisata = intent.getIntExtra("IDWisata",0)

        var etnamaw = findViewById<EditText>(R.id.etNama)
        var etdeskripsiw = findViewById<EditText>(R.id.etDeskripsi)
        var ethargaw = findViewById<EditText>(R.id.etHarga)
        var etalamatw = findViewById<EditText>(R.id.etAlamat)
        var bedit = findViewById<Button>(R.id.button5)

        //Radio Group
        var etkategoriw = findViewById<RadioGroup>(R.id.radioGroup)
        var radio : Int = 0

        //menentukan radio button mana yang dipilih
        etkategoriw.setOnCheckedChangeListener { group, checkedID ->
            if (checkedID == R.id.radioAlam) {
                radio = 1
            } else if (checkedID == R.id.radioBudaya) {
                radio = 2
            } else if (checkedID == R.id.radioMakanan) {
                radio = 3
            } else if (checkedID == R.id.radioBudaya) {
                radio = 4
            }

        }

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

        var argsUser = listOf<String>(idwisata.toString()).toTypedArray()
        var cursor : Cursor = reg.rawQuery("select * from wisata where ID = ?", argsUser)

        if(cursor.moveToNext()) {
            var IDWisata = cursor.getInt(cursor.getColumnIndex("ID").toInt())
            var NamaWisata = cursor.getString(cursor.getColumnIndex("nama").toInt())
            var DeskripsiWisata = cursor.getString(cursor.getColumnIndex("deskripsi").toInt())
            var HargaWisata = cursor.getInt(cursor.getColumnIndex("harga").toInt())
            var KategoriWisata = cursor.getInt(cursor.getColumnIndex("kategori").toInt())
            var AlamatWisata = cursor.getString(cursor.getColumnIndex("alamat").toInt())
            var fotoUser = cursor.getBlob(cursor.getColumnIndex("foto").toInt())

            etnamaw.setText(NamaWisata)
            etdeskripsiw.setText(DeskripsiWisata)
            ethargaw.setText(HargaWisata.toString())
            etalamatw.setText(AlamatWisata)
            if(KategoriWisata == 1){
                etkategoriw.check(R.id.radioAlam)
            }
            else if(KategoriWisata == 2){
                etkategoriw.check(R.id.radioBudaya)
            }
            else if(KategoriWisata == 3){
                etkategoriw.check(R.id.radioMakanan)
            }
            else if(KategoriWisata == 4){
                etkategoriw.check(R.id.radioSejarah)
            }

            bedit.setOnClickListener{
                var namaedit = etnamaw.text.toString()
                var deskripsiedit = etdeskripsiw.text.toString()
                var hargaedit = Integer.parseInt(ethargaw.getText().toString())
                var alamatedit = etalamatw.text.toString()


                db.updateNameWisata(namaedit,deskripsiedit,alamatedit,hargaedit,radio,IDWisata)
                Toast.makeText(getApplicationContext(),"Berhasil disimpan", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(getApplicationContext(),"Data Wisata tidak ada", Toast.LENGTH_SHORT).show()
            bedit.setEnabled(false);
        }







    }

    fun fBack(view: View) {
        val toBeranda = Intent(this,BerandaOwner::class.java)
        startActivity(toBeranda)
    }

}