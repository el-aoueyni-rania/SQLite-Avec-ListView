package com.example.storagesqlite


import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.Cursor
import android.database.SQLException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.content.contentValuesOf
import com.example.storagesqlite.UserDbHelper.Companion.COLUMN_NAME


class MainActivity : AppCompatActivity() {
    lateinit var lisetusers: ArrayList<String>
    lateinit var userDbhelper: UserDbHelper
    lateinit var nom: EditText
    lateinit var liste: ListView
    lateinit var date: EditText
    lateinit var email: EditText
    lateinit var classe: EditText
    lateinit var submit: Button
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userDbhelper = UserDbHelper(this)
        nom = findViewById(R.id.nom)
        date = findViewById(R.id.date)
        email = findViewById(R.id.email)
        classe = findViewById(R.id.classe)
        submit = findViewById(R.id.submit)
        liste = findViewById(R.id.liste)
        lisetusers = showallusers()
        adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lisetusers)
        liste.adapter = adapter


    }

    fun addUser(view: View) {
        val nom = nom.text.toString()
        val date = date.text.toString()
        val email = email.text.toString()
        val classe = classe.text.toString()

        val db = userDbhelper.db
        val values = ContentValues()
        values.put(UserDbHelper.COLUMN_NAME, nom)
        values.put(UserDbHelper.COLUMN_DATE, date)
        values.put(UserDbHelper.COLUMN_classe, classe)
        values.put(UserDbHelper.COLUMN_email, email)
        val newrow = db.insert(UserDbHelper.TABLE_NAME, null, values)
        Toast.makeText(this, newrow.toString(), Toast.LENGTH_SHORT).show()
        lisetusers.clear()
        lisetusers.addAll(showallusers())
        adapter.notifyDataSetChanged();
        /*   val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lisetusers)
           liste.adapter=adapter*/
    }

    @SuppressLint("Range")
    fun showallusers(): ArrayList<String> {
        val users1 = ArrayList<UserModel>()
        val users = ArrayList<String>()
        val db = userDbhelper.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("select * from " + UserDbHelper.TABLE_NAME, null)


            if (cursor?.moveToFirst() == true) {
                while (cursor.isAfterLast == false) {


                    var nom = cursor.getString(cursor.getColumnIndex(UserDbHelper.COLUMN_NAME))
                    var email = cursor.getString(cursor.getColumnIndex(UserDbHelper.COLUMN_email))
                    var classe = cursor.getString(cursor.getColumnIndex(UserDbHelper.COLUMN_classe))
                    var date = cursor.getString(cursor.getColumnIndex(UserDbHelper.COLUMN_DATE))
                    var user = UserModel(nom, classe, date, email)
                    users.add(nom)
                    cursor.moveToNext()


                }
            }
        }catch (e:SQLException){

        }

        return users;

    }
    }