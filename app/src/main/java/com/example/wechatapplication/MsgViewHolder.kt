package com.example.wechatapplication

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

sealed class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view)

class LeftViewHolder(view: View) : MsgViewHolder(view) {
    val leftImg: ImageView = view.findViewById(R.id.MsgLeftImg)
    val leftText: TextView = view.findViewById(R.id.MsgLeftText)
}

class RightViewHolder(view: View) : MsgViewHolder(view) {
    val rightImg: ImageView = view.findViewById(R.id.MsgRightImg)
    val rightText: TextView = view.findViewById(R.id.MsgRightText)
}