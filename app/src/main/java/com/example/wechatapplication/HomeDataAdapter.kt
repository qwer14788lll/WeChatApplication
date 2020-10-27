package com.example.wechatapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HomeDataAdapter(private val homeDataList: List<HomeData>) : RecyclerView.Adapter<HomeDataAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val userImage: ImageView = view.findViewById(R.id.UserImage)
        val userName: TextView = view.findViewById(R.id.UserName)
        val userMsg: TextView = view.findViewById(R.id.UserMsg)
        val userTime: TextView = view.findViewById(R.id.UserTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item, parent, false)
        val viewHolder = ViewHolder(view)
        viewHolder.itemView.setOnClickListener {
            parent.context.startActivity(Intent(parent.context, MsgActivity::class.java))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val homeData = homeDataList[position]
        holder.userImage.setImageResource(homeData.ImageID)
        holder.userName.text = homeData.UserName
        holder.userMsg.text = homeData.UserMsg
        holder.userTime.text = homeData.UserTime
    }

    override fun getItemCount() = homeDataList.count()
}