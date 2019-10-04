package com.example.orderskuy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orderskuy.data.Pref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.login.*

class Login : AppCompatActivity() {

    lateinit var pref: Pref
    lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        pref = Pref(this)
        fAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        if (!pref.cekStatus()!!) {
        } else {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        if (fAuth.currentUser != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
        }

        tvRegister.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }

        btnLogin.setOnClickListener {
            val email = etEmaill.text.toString()
            val password = etPass.text.toString()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Fill All Data", Toast.LENGTH_SHORT).show()
                btnLogin.visibility = View.VISIBLE
            } else {
                fAuth.signInWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        pref.setStatus(true)
                        FirebaseDatabase.getInstance().getReference("user")
                            .addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onCancelled(p0: DatabaseError) {

                                }

                                override fun onDataChange(p0: DataSnapshot) {
                                    val user = fAuth.currentUser
                                    updateUI(user)
                                }

                            })
                    }
                    .addOnFailureListener {
                        Toast.makeText(
                            this,
                            "Username atau Password salah!",
                            Toast.LENGTH_SHORT
                        ).show()
                        btnLogin.visibility = View.VISIBLE
                    }
            }
        }
    }

    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            pref.saveUID(user.uid)
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            Log.e("TAG_ERROR", "user tidak ada")
        }
    }

}