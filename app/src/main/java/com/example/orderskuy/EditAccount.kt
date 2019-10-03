package com.example.orderskuy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.orderskuy.data.Pref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.edit_account.*

class EditAccount : AppCompatActivity() {

    lateinit var dbRef: DatabaseReference
    private lateinit var fAuth: FirebaseAuth
    lateinit var preferences: Pref
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_account)
        preferences = Pref(this)
        fAuth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        FirebaseDatabase.getInstance().getReference("user/${fAuth.uid}")
            .child("username").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    et_edit_username.setText(p0.value.toString())
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
        FirebaseDatabase.getInstance().getReference("user/${fAuth.uid}")
            .child("email").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    et_edit_email.setText(p0.value.toString())
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
        FirebaseDatabase.getInstance().getReference("user/${fAuth.uid}")
            .child("password").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    et_edit_password.setText(p0.value.toString())
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
        btn_save.setOnClickListener {
            val uidUser = fAuth.currentUser?.uid
            val counter = preferences.getUID()
            dbRef = FirebaseDatabase.getInstance().reference
            dbRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.e("Error", p0.message)
                }

                override fun onDataChange(p0: DataSnapshot) {
                    val editusername = et_edit_username.text.toString()
                    val editpass = et_edit_password.text.toString()
                    val editmail = et_edit_email.text.toString()

                    dbRef.child("user/$uidUser/username").setValue(editusername)
                    dbRef.child("user/$uidUser/email").setValue(editmail)
                    dbRef.child("user/$uidUser/password").setValue(editpass)
                    Toast.makeText(this@EditAccount, "Sukses", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this@EditAccount, MainActivity::class.java))
                }
            })
        }
    }


}