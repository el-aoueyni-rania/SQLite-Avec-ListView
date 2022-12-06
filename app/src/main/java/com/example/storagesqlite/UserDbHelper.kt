package com.example.storagesqlite
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context:Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    public val db=writableDatabase

    companion object {

        public val DATABASE_VERSION = 1
        public val DATABASE_NAME = "users.db"
        public val  TABLE_NAME="users"
        public val COLUMN_USER_ID="id"
        public val COLUMN_NAME="nom"
        public val COLUMN_email="email"
        public val COLUMN_classe="classe"
        public val COLUMN_DATE="date"

        public val SQL_CREATE_ENTRIES =
            " CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_NAME + " TEXT," +
                    COLUMN_email + " TEXT," +
                    COLUMN_classe + " TEXT," +
                    COLUMN_DATE + " TEXT)"



        public val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL(SQL_CREATE_ENTRIES)
        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}