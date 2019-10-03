package com.example.orderskuy

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orderskuy.data.Pref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.regsiter.*

class Register : AppCompatActivity() {

    lateinit var pref: Pref
    lateinit var fAuth: FirebaseAuth
    lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        fAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.regsiter)

        btnRegister.setOnClickListener {

            val username = findViewById<EditText>(R.id.etUsername).text.toString()
            val email = findViewById<EditText>(R.id.etEmail).text.toString()
            val password = findViewById<EditText>(R.id.etPassword).text.toString()
            if (username.isEmpty() || username == "" || email.isEmpty() || email == "" || password.isEmpty() || password == "") {
                Toast.makeText(this, "Fill All Data", Toast.LENGTH_SHORT).show()
            } else {
                fAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            addUserToFirebase(username, email, password)
                            Toast.makeText(this, "Register Berhasil!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Register, Login::class.java))
                        } else {
                            Toast.makeText(this, "GAGAL", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }

    private fun addUserToFirebase(username: String, email: String, password: String) {
        val uid = fAuth.currentUser?.uid
        dbRef = FirebaseDatabase.getInstance().getReference("user/$uid")
        dbRef.child("/id").setValue(uid)
        dbRef.child("/username").setValue(username)
        dbRef.child("/email").setValue(email)
        dbRef.child("/password").setValue(password)
    }
}