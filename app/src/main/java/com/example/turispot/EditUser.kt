package com.example.turispot

import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class EditUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_user)

        var ID = intent.getIntExtra("ID",0)

        var etnama = findViewById<EditText>(R.id.EditNama)
        var etusername = findViewById<EditText>(R.id.EditUsername)
        var etpassword = findViewById<EditText>(R.id.EditPassword)
        var etemail = findViewById<EditText>(R.id.EditEmail)

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

        var argsUser = listOf<String>(ID.toString()).toTypedArray()
        var cursorOwner : Cursor = reg.rawQuery("SELECT * FROM user WHERE id = ?", argsUser)

        if(cursorOwner.moveToNext()) {
            try{
                var namauserdb = cursorOwner.getString(cursorOwner.getColumnIndex("nama").toInt())
                var usernamedb = cursorOwner.getString(cursorOwner.getColumnIndex("username").toInt())
                var emaildb = cursorOwner.getString(cursorOwner.getColumnIndex("email").toInt())

                var etnama = findViewById<EditText>(R.id.EditNama).setText(namauserdb)
                var etusername = findViewById<EditText>(R.id.EditUsername).setText(usernamedb)
                var etemail = findViewById<EditText>(R.id.EditEmail).setText(emaildb)




            }catch (e : Exception){
                e.printStackTrace()
            }

        }else{
            Toast.makeText(getApplicationContext(),"System gagal mengambil dari database", Toast.LENGTH_SHORT).show()
        }

        var bsimpan = findViewById<Button>(R.id.bSimpan)
        bsimpan.setOnClickListener{

            var editnama = etnama.text.toString()
            var editusername = etusername.text.toString()
            var editemail = etemail.text.toString()
            var editpassword = etpassword.text.toString()

            db.updateNameUser(editnama, editusername,editpassword,editemail,ID)
            Toast.makeText(getApplicationContext(),"Berhasil disimpan", Toast.LENGTH_SHORT).show()
        }


    }


}