package com.example.turispot

import android.content.Intent
import android.database.Cursor
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.*
import java.lang.Exception

class login : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        merubahTeks()


        //textPassword dan Username
        var textLoginPass = findViewById<EditText>(R.id.textLoginPassword)
        var textLoginUname = findViewById<EditText>(R.id.textLoginUsername)

        //Radio Group
        var groupLoginRadio = findViewById<RadioGroup>(R.id.radioGroupLogin)

        //button Login
        var buttonLogin = findViewById<Button>(R.id.btnLogin)

        //mendeklarasi DB dan mengimport db
        var db = DatabaseHelper(this, "turispot.db", 1)

        //mendeklarasi variabel untuk radio button
        var radio : String? = null

        try{
            db.OpenDatabase()
        }catch (e : Exception){
            e.printStackTrace()
        }



        //menentukan radio button mana yang dipilih
        groupLoginRadio.setOnCheckedChangeListener { group, checkedID ->
            if (checkedID == R.id.radioLoginPengguna) {
                radio = "User"
            } else if (checkedID == R.id.radioLoginPemilik) {
                radio = "Owner"
            } else{
                radio = null
            }
        }

        //deklarasi untuk db login
        var reg = db.readableDatabase


        buttonLogin.setOnClickListener {
            if(textLoginPass.text.isEmpty() || textLoginUname.text.isEmpty()){
                //toast jika ada kolom yang belum terisi
                Toast.makeText(applicationContext, "Silahkan isi formulir dengan benar!", Toast.LENGTH_LONG).show()
            } else{
                if (radio.equals("User")){
                    var username : String = textLoginUname.text.toString().trim()
                    var pass: String = textLoginPass.text.toString().trim()

                    var argsUser = listOf<String>(username, pass).toTypedArray()
                    var cursor : Cursor = reg.rawQuery("select * from user where username = ? and password = ?", argsUser)

                    if(cursor.moveToNext()){
                        var IDUser = cursor.getInt(cursor.getColumnIndex("ID").toInt())
                        var EXPUser = cursor.getInt(cursor.getColumnIndex("exp").toInt())
                        var levelUser = cursor.getInt(cursor.getColumnIndex("level").toInt())

                        var emailUser = cursor.getString(cursor.getColumnIndex("email").toInt())
                        var namaUser = cursor.getString(cursor.getColumnIndex("nama").toInt())
                        var fotoUser = cursor.getString(cursor.getColumnIndex("foto").toInt())

                        val intent = Intent(this, Beranda::class.java ).apply {
                            putExtra("ID",IDUser)
                            putExtra("EXPUser",EXPUser)
                            putExtra("levelUser",levelUser)
                            putExtra("emailUser",emailUser)
                            putExtra("namaUser",namaUser)
                            putExtra("fotoUser",fotoUser)
                        }
                        Toast.makeText(getApplicationContext(),"Berhasil Login!",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else{
                        Toast.makeText(getApplicationContext(),"Username Atau Password Salah!",Toast.LENGTH_SHORT).show()
                    }

                } else if (radio.equals("Owner")){
                    var usernameOwner : String = textLoginUname.text.toString().trim()
                    var passOwner: String = textLoginPass.text.toString().trim()

                    var argsOwner = listOf<String>(usernameOwner, passOwner).toTypedArray()
                    var cursorOwner : Cursor = reg.rawQuery("select * from pemilik where username = ? and password = ?", argsOwner)

                    if(cursorOwner.moveToNext()){
                        var IDOwner = cursorOwner.getInt(cursorOwner.getColumnIndex("ID").toInt())

                        var alamatOwner = cursorOwner.getString(cursorOwner.getColumnIndex("alamat").toInt())
                        var emailOwner = cursorOwner.getString(cursorOwner.getColumnIndex("email").toInt())
                        var birthOwner = cursorOwner.getString(cursorOwner.getColumnIndex("tanggal_lahir").toInt())
                        var namaOwner = cursorOwner.getString(cursorOwner.getColumnIndex("nama").toInt())
                        var fotoOwner = cursorOwner.getString(cursorOwner.getColumnIndex("foto").toInt())

                        val intent = Intent(this, BerandaOwner::class.java ).apply {
                            putExtra("IDOwner",IDOwner)
                            putExtra("emailOwner",emailOwner)
                            putExtra("birthOwner",birthOwner)
                            putExtra("alamatOwner",alamatOwner)
                            putExtra("namaOwner",namaOwner)
                            putExtra("fotoOwner",fotoOwner)
                        }
                        Toast.makeText(getApplicationContext(),"Berhasil Login!",Toast.LENGTH_SHORT).show()
                        startActivity(intent)
                    } else{
                        Toast.makeText(getApplicationContext(),"Username Atau Password Salah!",Toast.LENGTH_SHORT).show()
                    }

                }else{
                    //toast jika belum memilih tipe login
                    Toast.makeText(getApplicationContext(),"Masukkan tipe Login!",Toast.LENGTH_SHORT).show()
                }
            }
        }



    }

    fun merubahTeks(){
        //Menemukan text untuk diklik sebagian
        val textview = findViewById<TextView>(R.id.textKeRegister)

        //Membuat teksnya
        val spannableString = SpannableString("Belum punya akun? Buat Akun")

        //Menentukan teks dapat diklik dan method apa yang akan dijalankan

        val clickableSpan = object : ClickableSpan() {
            //Method yang akan dijalankan saat diklik, mengganti layar activity
            override fun onClick(view: View) {
                val keReg = Intent(this@login, Register::class.java)
                startActivity(keReg)
            }

            override fun updateDrawState(ds: TextPaint) {
                //merubah warna teks yang dapat diklik dan menghilangkan garis bawah
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#326E6C")
                ds.isUnderlineText = false
            }

        }

        //Mengaplikasikan teks yang dapat diklik
        spannableString.setSpan(clickableSpan, 18, 27, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        //Mengganti teks pada textview agar dapat diklik
        textview.text = spannableString

        //mmerubah movement method
        textview.movementMethod = LinkMovementMethod.getInstance()
    }
}