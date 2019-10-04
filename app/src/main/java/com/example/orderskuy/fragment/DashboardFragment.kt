package com.example.orderskuy.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.orderskuy.AddOrder
import com.example.orderskuy.DataOrder
import com.example.orderskuy.R
import com.example.orderskuy.data.Pref
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.dashboard.*

class DashboardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.dashboard, container, false)
    }

    lateinit var fAuth: FirebaseAuth
    lateinit var preferences: Pref
    lateinit var storageReference: StorageReference
    lateinit var firebaseStorage: FirebaseStorage
    var uidCheck = "btXiRU8170YrlgYHdjQiXaZWz4k1"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = Pref(context!!)
        fAuth = FirebaseAuth.getInstance()
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = firebaseStorage.reference

        addOrder.setOnClickListener {
            startActivity(Intent(activity!!, AddOrder::class.java))
        }
        dataOrderImg.setOnClickListener {
            startActivity(Intent(activity!!, DataOrder::class.java))
        }
    }

    companion object {
        fun newInstance(): DashboardFragment {
            val fragment = DashboardFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }

    }

}