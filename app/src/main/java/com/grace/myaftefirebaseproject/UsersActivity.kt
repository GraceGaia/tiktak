package com.grace.myaftefirebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersActivity : AppCompatActivity() {
    var listWatu:ListView? = null
    var adapter:CustomAdapter? = null
    var users:ArrayList<User>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
        listWatu = findViewById(R.id.mListPeople)
        users = ArrayList()
        adapter = CustomAdapter(this,users!!)
        //Access the table in the database to fetch data
        var reference = FirebaseDatabase.getInstance().getReference().child("Users")
        //Start receiving data using a ValueEventListener
        reference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                users!!.clear()
                for (snap in snapshot.children){
                    var user = snap.getValue(User::class.java)
                    users!!.add(user!!)
                }
                adapter!!.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Sorry,DB is locked",Toast.LENGTH_LONG).show()
            }
        })

        listWatu!!.adapter = adapter
    }
}