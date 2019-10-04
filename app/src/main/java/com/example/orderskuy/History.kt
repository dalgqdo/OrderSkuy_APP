package com.example.orderskuy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.orderskuy.adapter.OrderAdapter
import com.example.orderskuy.data.Pref
import com.example.orderskuy.model.OrderModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.history.*
import java.util.*

class History : AppCompatActivity() {

    private lateinit var fAuth: FirebaseAuth
    lateinit var pref: Pref
    private var orderAdapter: OrderAdapter? = null
    private var recyclerView: RecyclerView? = null
    private var list: MutableList<OrderModel> = ArrayList()
    lateinit var dbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)
        fAuth = FirebaseAuth.getInstance()
        pref = Pref(this)
        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView = findViewById(R.id.recyclerViewOrder)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        dbRef = FirebaseDatabase.getInstance().getReference("order/")
        dbRef.orderByChild("status").equalTo("Ready").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(data: DataSnapshot) {
                list = ArrayList()
                for (dataSnapshot in data.children) {
                    val addDataAll = dataSnapshot.getValue(OrderModel::class.java)
                    addDataAll!!.key = dataSnapshot.key
                    if (addDataAll.userid == fAuth.currentUser?.uid) {
                        val count = data.childrenCount.toString().toInt()
//                        if (count > 0) {
//                            fab.hide()
//                        } else {
//                            fab.show()
//                        }
                        Log.e("COUNTCHILD", count.toString())
                        list.add(addDataAll)
                    }
                    Log.e("c", addDataAll.name)
                }
                orderAdapter = OrderAdapter(this@History, list)
                recyclerView!!.adapter = orderAdapter
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e(
                    "TAG_ERROR", p0.message
                )
            }
        })

        btn_back.setOnClickListener {
            startActivity(Intent(this@History, MainActivity::class.java))
        }


    }

}