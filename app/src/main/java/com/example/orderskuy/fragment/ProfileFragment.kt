package com.example.orderskuy.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.orderskuy.EditAccount
import com.example.orderskuy.History
import com.example.orderskuy.Login
import com.example.orderskuy.R
import com.example.orderskuy.data.Pref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.account.*

class ProfileFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.account, container, false)
    }

    lateinit var fAuth: FirebaseAuth
    lateinit var dbRef: DatabaseReference
    lateinit var pref: Pref
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage

    lateinit var filePathImage: Uri
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = Pref(context!!)
        fAuth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference
        FirebaseDatabase.getInstance().getReference("user/${fAuth.uid}")
            .child("username").addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(p0: DataSnapshot) {
                    tv_name.text = p0.value.toString()
                }

                override fun onCancelled(p0: DatabaseError) {
                }
            })
        lyt_edit_profile.setOnClickListener {
            startActivity(Intent(activity!!, EditAccount::class.java))
        }

        history.setOnClickListener {
            startActivity(Intent(activity!!, History::class.java))
        }

        fAuth = FirebaseAuth.getInstance()
        lyt_logout.setOnClickListener {
            pref.setStatus(false)
            fAuth.signOut()
            val intent = Intent(context, Login::class.java)
            startActivity(intent)
            activity!!.finish()
        }
    }

    companion object {
        fun newInstance(): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

}