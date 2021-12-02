package com.example.turispot



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
import android.widget.Button
import android.widget.TextView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var buttonLogin = findViewById<Button>(R.id.btnLogin)

        buttonLogin.setOnClickListener {
            startActivity(Intent(this@MainActivity, login::class.java))
        }
        merubahTeks()

    }

    fun merubahTeks(){
        //Menemukan text untuk diklik sebagian
        val textview = findViewById<TextView>(R.id.textSignup)

        //Membuat teksnya
        val spannableString = SpannableString("Belum punya akun? Buat Akun")

        //Menentukan teks dapat diklik dan method apa yang akan dijalankan

        val clickableSpan = object : ClickableSpan() {
            //Method yang akan dijalankan saat diklik, mengganti layar activity
            override fun onClick(view: View) {
                val keReg = Intent(this@MainActivity, Register::class.java)
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

