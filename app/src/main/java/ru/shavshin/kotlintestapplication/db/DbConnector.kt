package ru.shavshin.kotlintestapplication.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ru.shavshin.kotlintestapplication.model.User

class DbConnector(val context: Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "My_db", factory, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE users(" +
                "id INT PRIMARY KEY, " +
                "login TEXT, " +
                "email TEXT, " +
                "password TEXT)"

        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val query = "DROP TABLE IF EXISTS users"
        db!!.execSQL(query)
        onCreate(db)
    }

    fun registerUser(user: User) {
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("password", user.pass)

        val db = this.writableDatabase
        db.insert("users", null, values)
        db.close()
    }

    fun getUser(login: String, pass: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM users WHERE login = '$login' AND password = '$pass'"

        val result = db.rawQuery(query, null)
        return result.moveToFirst()
    }
}