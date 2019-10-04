package com.example.orderskuy.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.orderskuy.R
import com.example.orderskuy.data.Pref
import com.example.orderskuy.model.OrderModel
import com.example.orderskuy.model.UserModel

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    lateinit var mCtx: Context
    lateinit var itemOrder: List<OrderModel>
    lateinit var user: List<UserModel>
    lateinit var pref: Pref

    constructor()
    constructor(mCtx: Context, list: List<OrderModel>) {
        this.mCtx = mCtx
        this.itemOrder = list
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): OrderViewHolder {
        val view: View = LayoutInflater.from(p0.context)
            .inflate(R.layout.content_order, p0, false)
        val bukuViewHolder = OrderViewHolder(view)
        return bukuViewHolder
    }

    override fun getItemCount(): Int {
        return itemOrder.size
    }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val orderModel: OrderModel = itemOrder.get(position)
//        val userModel: UserModel = user.get(position)
        holder.tv_name.text = orderModel.name
        holder.tv_startTime.text = orderModel.startTime.toString()
        holder.tv_endTime.text = orderModel.endTime.toString()
        holder.tv_seat.text = orderModel.seat.toString()
        holder.tv_status.text = orderModel.status.toString()
    }

    inner class OrderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var llOrder: LinearLayout
        var tv_name: TextView
        var tv_startTime: TextView
        var tv_endTime: TextView
        var tv_seat: TextView
        var tv_status: TextView

        init {
            llOrder = itemView.findViewById(R.id.llOrder)
            tv_name = itemView.findViewById(R.id.tvUsername)
            tv_startTime = itemView.findViewById(R.id.startTimeText)
            tv_endTime = itemView.findViewById(R.id.endTimeText)
            tv_seat = itemView.findViewById(R.id.tvSeat)
            tv_status = itemView.findViewById(R.id.tv_status_order)
        }
    }
}