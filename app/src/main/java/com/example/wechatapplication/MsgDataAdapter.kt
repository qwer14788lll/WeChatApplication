package com.example.wechatapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class MsgDataAdapter(private var msgDataList: List<MsgData>) : RecyclerView.Adapter<MsgViewHolder>() {

    //在该方法中返回当前消息所对应的消息类型
    override fun getItemViewType(position: Int) = msgDataList[position].type

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        if (viewType == MsgData.TYPE_LEFT) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_left_item, parent, false)
            LeftViewHolder(view)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.msg_right_item, parent, false)
            val viewHolder = RightViewHolder(view)
            //长按事件具有返回值，true代表该次事件回调执行到此结束，false代表追加执行点击事件
            viewHolder.itemView.setOnLongClickListener {
                //Toast.makeText(parent.context, viewHolder.adapterPosition.toString(), Toast.LENGTH_LONG).show()
                //移除数据源中的该项
                msgDataList -= msgDataList[viewHolder.adapterPosition]
                //从列表控件中移除该项
                notifyItemRemoved(viewHolder.adapterPosition)
                //局部刷新，从被移除项的位置到后面的数据这个范围
                //notifyItemRangeChanged(viewHolder.adapterPosition, itemCount)
                true
            }
            viewHolder
        }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msgData = msgDataList[position]
        when (holder) {
            is LeftViewHolder -> {
                holder.leftImg.setImageResource(msgData.ImageID)
                holder.leftText.text = msgData.Text
            }
            is RightViewHolder -> {
                holder.rightImg.setImageResource(msgData.ImageID)
                holder.rightText.text = msgData.Text
            }
        }
    }

    override fun getItemCount() = msgDataList.count()

    fun getList() = msgDataList
}