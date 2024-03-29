package com.example.turispot

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import java.io.FileOutputStream
import java.io.OutputStream
import java.lang.Exception


//kelas databasehelper dengan beberapa constructor dan mewarisi SQLiteOpenHelper
class DatabaseHelper(var mContext: Context, var dbName: String, version: Int) : SQLiteOpenHelper(
    mContext, dbName, null, version
) {
    //direktori db
    var dbPath: String

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {}
    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    //inisialisasi direktori db asal
    init {
        dbPath = "/data/data/" + "com.example.turispot" + "/databases/"
    }

    //mengecek apakah sudah ada db atau belum
    fun CheckDb() {
        var checkDb: SQLiteDatabase? = null
        val filepath = dbPath + dbName
        try {
            checkDb = SQLiteDatabase.openDatabase(filepath, null, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (checkDb != null) {
        } else {
            CopyDatabase()
        }
    }

    //menyalin db jika ada
    private fun CopyDatabase() {
        this.writableDatabase
        try {
            val ios = mContext.assets.open(dbName)
            val os: OutputStream = FileOutputStream(dbPath + dbName)
            val buffer = ByteArray(1024)
            var len: Int
            while (ios.read(buffer).also { len = it } > 0) {
                os.write(buffer, 0, len)
            }
            os.flush()
            ios.close()
            os.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        Log.d("CopyDb", "Database tersalin")
    }

    //membuka db
    fun OpenDatabase() {
        val filepath = dbPath + dbName
        SQLiteDatabase.openDatabase(filepath, null, 0)
    }

    fun updateName(nama : String, tanggal : String, email : String, alamat : String, id : Int){
        var db : SQLiteDatabase  = this.writableDatabase
        var query : String = "UPDATE pemilik SET " + "nama = '" + nama + "',tanggal_lahir = '" + tanggal + "',email = '" + email + "',alamat = '" + alamat + "' WHERE ID = " + id
        db.execSQL(query)
    }

    fun updateNameUser(nama : String, username : String, password : String, email : String, id : Int){
        var db : SQLiteDatabase  = this.writableDatabase
        var query : String = "UPDATE user SET " + "nama = '" + nama + "',username = '" + username + "',email = '" + email + "',password = '" + password + "' WHERE ID = " + id
        db.execSQL(query)
    }

    fun updateNameWisata(nama : String, deskripsi : String, alamat : String, harga : Int,kategori : Int, id : Int){
        var db : SQLiteDatabase  = this.writableDatabase
        var query : String = "UPDATE wisata SET " + "nama = '" + nama + "',deskripsi = '" + deskripsi + "',alamat = '" + alamat + "',harga = '" + harga + "',kategori ='" + kategori + "' WHERE ID = " + id
        db.execSQL(query)
    }

    fun updateUserExp(id : Int){
        var db : SQLiteDatabase  = this.writableDatabase
        var query : String = "UPDATE user SET " + "exp = exp + 20 WHERE ID = " + id
        db.execSQL(query)
    }

    fun updateUserLevel(id : Int){
        var db : SQLiteDatabase  = this.writableDatabase
        var query : String = "UPDATE user SET " + "exp = exp - 100 ,level = level + 1 WHERE ID = " + id
        db.execSQL(query)
    }




}