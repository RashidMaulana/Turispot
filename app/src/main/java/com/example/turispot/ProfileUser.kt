package com.example.turispot

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.example.turispot.R.layout.*
import java.lang.Exception

class ProfileUser : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_profile_user)



        var IDUser = intent.getIntExtra("ID",0)
        var namaUser = intent.getStringExtra("namaUser")
        var emailUser = intent.getStringExtra("emailUser")
        var expUser = intent.getIntExtra("EXPUser",0)
        var levelUser = intent.getIntExtra("levelUser",0)




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

        var argsOwner = listOf<String>(IDUser.toString()).toTypedArray()
        var cursorOwner : Cursor = reg.rawQuery("select * from user where ID = ?", argsOwner)

        if(cursorOwner.moveToNext()) {
            var IDUserdb = cursorOwner.getInt(cursorOwner.getColumnIndex("ID").toInt())

            var NamaUserdb = cursorOwner.getString(cursorOwner.getColumnIndex("nama").toInt())
            var EmailUserdb = cursorOwner.getString(cursorOwner.getColumnIndex("email").toInt())
            var ExpUserdb =
                cursorOwner.getInt(cursorOwner.getColumnIndex("exp").toInt())
            var LevelUserdb = cursorOwner.getInt(cursorOwner.getColumnIndex("level").toInt())

            var textusername = findViewById<TextView>(R.id.tvNama).apply {
                text = NamaUserdb
            }
            var textlvluser = findViewById<TextView>(R.id.tvLevelUser).apply {
                text = "Level " + LevelUserdb.toString()
            }
            var pgexpbar = findViewById<ProgressBar>(R.id.pgLevelBar).apply {
                progress = ExpUserdb
                visibility = View.VISIBLE
            }
        }else{
            Toast.makeText(getApplicationContext(),"Anda belum login", Toast.LENGTH_SHORT).show()
        }

    }

    fun PindahAchievementUser (view : View){
        var IDUser = intent.getIntExtra("ID",0)
        var namaUser = intent.getStringExtra("namaUser")
        val intent = Intent(this, AchievementUser::class.java).apply {
            putExtra("ID", IDUser)
            putExtra("namaUser", namaUser)
        }
        startActivity(intent)

    }

    fun PindahEditUser (view : View){
        var IDUser = intent.getIntExtra("ID",0)
        val intent = Intent(this, EditUser::class.java).apply {
            putExtra("ID", IDUser)

        }
        startActivity(intent)


    }
}