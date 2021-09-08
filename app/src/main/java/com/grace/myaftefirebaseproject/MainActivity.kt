package com.grace.myaftefirebaseproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var editTextName:EditText? = null
    var editTextEmail:EditText? = null
    var editTextNumber:EditText? = null
    var buttonSave:Button? = null
    var buttonView:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.mEditName)
        editTextEmail = findViewById(R.id.mEditEmail)
        editTextNumber = findViewById(R.id.mEditIdNumber)
        buttonSave = findViewById(R.id.mBtnSave)
        buttonView = findViewById(R.id.mBtnView)

        //Set the on click listeners to buttons
        buttonSave!!.setOnClickListener {
            var name = editTextName!!.text.toString().trim()
            var email = editTextEmail!!.text.toString().trim()
            var idNumber = editTextNumber!!.text.toString().trim()
            if (name.isEmpty()){
                editTextName!!.setError("Please fill in this input")
                editTextName!!.requestFocus()
            }else if (email.isEmpty()){
                editTextEmail!!.setError("Please fill in this input")
                editTextEmail!!.requestFocus()
            }else if (idNumber.isEmpty()){
                editTextNumber!!.setError("Please fill in this input")
                editTextNumber!!.requestFocus()
            }else{
                //Proceed to save data
                    var time = System.currentTimeMillis().toString()//how to get current time in milliseconds
                var userData = User(name,email,idNumber,time)
                var databaseRef = FirebaseDatabase.getInstance().getReference().child("Users/$time")
                //Finally store data
                databaseRef.setValue(userData).addOnCompleteListener { task->
                    if (task.isSuccessful){
                        Toast.makeText(this,"User saved successfully",
                        Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Saving failed",
                        Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
        buttonView!!.setOnClickListener {
            var usersIntent = Intent(this,UsersActivity::class.java)
            startActivity(usersIntent)
        }
    }
}