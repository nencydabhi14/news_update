package com.example.newsupdate

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Db_save(context: Context?) : SQLiteOpenHelper(context, "Save.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var query =
            "CREATE TABLE StoreSave(id INTEGER PRIMARY KEY AUTOINCREMENT,author TEXT,title TEXT,description TEXT,url TEXT,urlToImage TEXT,publishedAt TEXT ,content TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}


    fun insertData(
        author: String,
        title: String,
        description: String,
        url: String,
        urlToImage: String,
        publishedAt: String,
        content: String
    ) {

        var db = writableDatabase

        var cv = ContentValues()
        cv.put("author", author)
        cv.put("title", title)
        cv.put("description", description)
        cv.put("url", url)
        cv.put("urlToImage", urlToImage)
        cv.put("publishedAt", publishedAt)
        cv.put("content", content)

        var res = db.insert("StoreSave", null, cv)

        Log.e("TAG", "insertData: $author")

        println(res)

    }

    @SuppressLint("Range")
    fun ReadData(): ArrayList<ModelSave> {

        var list = arrayListOf<ModelSave>()

        var db = readableDatabase
        var query = "SELECT * FROM StoreSave"

        var cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                var author = cursor.getString(cursor.getColumnIndex("author"))
                var title = cursor.getString(cursor.getColumnIndex("title"))
                var description = cursor.getString(cursor.getColumnIndex("description"))
                var url = cursor.getString(cursor.getColumnIndex("url"))
                var urlToImage = cursor.getString(cursor.getColumnIndex("urlToImage"))
                var publishedAt = cursor.getString(cursor.getColumnIndex("publishedAt"))
                var content = cursor.getString(cursor.getColumnIndex("content"))

                var m1 =
                    ModelSave(id, author, title, description, url, urlToImage, publishedAt, content)
                list.add(m1)

            } while (cursor.moveToNext())
        }

        return list
    }
}