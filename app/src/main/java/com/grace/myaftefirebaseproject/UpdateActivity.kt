package com.grace.myaftefirebaseproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    var editTextName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextNumber: EditText? = null
    var buttonUpdate: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        editTextName = findViewById(R.id.mEditName)
        editTextEmail = findViewById(R.id.mEditEmail)
        editTextNumber = findViewById(R.id.mEditIdNumber)
        buttonUpdate = findViewById(R.id.mBtnUpdate)
        //Receive data on an intent
        var receivedName = intent.getStringExtra("w")
        var receivedEmail = intent.getStringExtra("x")
        var receivedIdNumber = intent.getStringExtra("y")
        var receivedId = intent.getStringExtra("z")
        //set the received data on to the edit texts so that the user can edit
        editTextName!!.setText(receivedName)
        editTextEmail!!.setText(receivedEmail)
        editTextNumber!!.setText(receivedIdNumber)
        //set the on click listener on button update to implement the update process
        buttonUpdate!!.setOnClickListener {
            //Receive the updated values from the edit texts
            var updatedName = editTextName!!.text.toString().trim()
            var updatedEmail = editTextEmail!!.text.toString().trim()
            var updatedIdNumber = editTextNumber!!.text.toString().trim()
            //Check if the user is resubmitting empty fields
            if (updatedName.isEmpty()) {
                editTextName!!.setError("Please fill this field")
                editTextName!!.requestFocus()
            } else if (updatedEmail.isEmpty()) {
                editTextEmail!!.setError("Please fill this field")
                editTextEmail!!.requestFocus()
            } else if (updatedIdNumber.isEmpty()) {
                editTextNumber!!.setError("Please fill this field")
                editTextNumber!!.requestFocus()
            } else {
                //Proceed to return the data back to the database
                var newUpdatedData = User(updatedName, updatedEmail, updatedIdNumber, receivedId!!)
                //Get the reference to the database and return the data
                var reference =
                    FirebaseDatabase.getInstance().getReference().child("Users/$receivedId")
                reference.setValue(newUpdatedData).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            "User updated successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "User updating failed",
                            Toast.LENGTH_LONG
                        ).show()
                        }
                    }
                }
            }
        }
    }