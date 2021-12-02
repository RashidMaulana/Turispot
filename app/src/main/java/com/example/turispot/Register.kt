package com.example.turispot

import android.content.ContentValues
import android.content.Intent
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

class Register : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //atribut nama, email, password
        var textNama = findViewById<EditText>(R.id.textNama)
        var textEmail = findViewById<EditText>(R.id.textEmail)
        var textPass = findViewById<EditText>(R.id.textPassword)
        var textUname = findViewById<EditText>(R.id.textUsername)

        //Radio Group
        var groupRadio = findViewById<RadioGroup>(R.id.radioGroup)

        //button
        var buttonRegister = findViewById<Button>(R.id.btnRegister)

        //mendeklarasi DB dan mengimport db
        var db = DatabaseHelper(this, "turispot.db", 1)
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


        //mendeklarasi variabel untuk radio button
        var radio : String? = null

        //menentukan radio button mana yang dipilih
        groupRadio.setOnCheckedChangeListener { group, checkedID ->
            if (checkedID == R.id.radioPengguna) {
                radio = "User"
            } else if (checkedID == R.id.radioPemilik) {
                radio = "Owner"
            } else{
                radio = null
            }
        }


        //memanggil method merubahText
        merubahText()

        //mendeklarasi variabel untuk mengedit db
        var reg = db.writableDatabase

        buttonRegister.setOnClickListener {
            if(textEmail.text.isEmpty() || textNama.text.isEmpty() || textPass.text.isEmpty() || textUname.text.isEmpty() ){
                    //toast jika ada kolom yang belum terisi
                    Toast.makeText(applicationContext, "Silahkan isi formulir dengan benar!",Toast.LENGTH_LONG).show()
                }else{

                    //memastikan pola email benar (sudah ada @mail.com/@gmail.com/@yahoo.com
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(textEmail.text.toString()).matches()){

                        if(radio.equals("User")){

                            //mengambil nilai dari edittext
                            var nama: String = textNama.text.toString().trim()
                            var username : String = textUname.text.toString().trim()
                            var email: String = textEmail.text.toString().trim()
                            var pass: String = textPass.text.toString().trim()

                            //Memasukkan nilai dari edittext ke variabel yang akan dikirim ke db
                            var cv = ContentValues()
                            cv.put("nama", nama)
                            cv.put("email", email)
                            cv.put("password", pass)
                            cv.put("username",username)

                            //Memasukkan ke db, tabel user
                            reg.insert("user", null, cv)
                            Toast.makeText(getApplicationContext(),"Selamat Pengguna Berhasil Terdaftar!",Toast.LENGTH_SHORT).show()


                            //mereset edittext jadi kosong
                            textNama.setText("")
                            textUname.setText("")
                            textEmail.setText("")
                            textPass.setText("")

                            //mengembalikan ke bagian nama
                            textNama.requestFocus()

                        } else if (radio.equals("Owner")){
                            //mengambil nilai dari edittext
                            var namaOwner : String = textNama.text.toString().trim()
                            var usernameOwner : String = textUname.text.toString().trim()
                            var emailOwner : String = textEmail.text.toString().trim()
                            var passOwner : String = textPass.text.toString().trim()


                            //Memasukkan nilai dari edittext ke variabel yang akan dikirim ke db
                            var vc = ContentValues()
                            vc.put("nama",namaOwner)
                            vc.put("username",usernameOwner)
                            vc.put("email",emailOwner)
                            vc.put("password",passOwner)

                            //mengirim ke db
                            reg.insert("pemilik",null,vc)
                            Toast.makeText(getApplicationContext(),"Selamat Pemilik Berhasil terdaftar!",Toast.LENGTH_SHORT).show()

                            //mereset editext
                            textNama.setText("")
                            textUname.setText("")
                            textEmail.setText("")
                            textPass.setText("")

                            //mengembalikan fokus ke nama
                            textNama.requestFocus()

                        }else{

                            //toast jika belum memilih tipe daftar
                            Toast.makeText(getApplicationContext(),"Masukkan tipe daftar",Toast.LENGTH_SHORT).show()
                        }
                    } else{

                        //toast jika email belum benar (belum ada @mail.com/@gmail.com/yahoo.com
                        Toast.makeText(getApplicationContext(), "Pola Email belum benar!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

    fun merubahText() {
        //Menemukan text untuk diklik sebagian
        val textview = findViewById<TextView>(R.id.textKeLogin)

        //Membuat teksnya
        val spannableString = SpannableString("Sudah punya akun? Log In")

        //Menentukan teks dapat diklik dan method apa yang akan dijalankan
        val clickableSpan = object : ClickableSpan() {
            //Method yang akan dijalankan saat diklik, mengganti layar activity
            override fun onClick(view: View) {
                startActivity(Intent(this@Register, login::class.java))
            }

            override fun updateDrawState(ds: TextPaint) {
                //merubah warna teks yang dapat diklik dan menghilangkan garis bawah
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#326E6C")
                ds.isUnderlineText = false
            }

        }
        //Mengaplikasikan teks yang dapat diklik
        spannableString.setSpan(clickableSpan, 18, 24, Spannable.SPAN_INCLUSIVE_INCLUSIVE)

        //Mengganti teks pada textview agar dapat diklik
        textview.text = spannableString

        //merubah movement method
        textview.movementMethod = LinkMovementMethod.getInstance()
    }
}







