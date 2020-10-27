package com.example.wechatapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_msg.*
import java.util.*

class MsgActivity : AppCompatActivity() {
    private var msgDataList = ArrayList<MsgData>()
    private lateinit var adapter: MsgDataAdapter

    private fun initMsgData() {
        repeat(2)
        {
            msgDataList.add(
                MsgData(
                    R.drawable.img_1,
                    "两个include的高度拉不动",
                    0
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_1,
                    "一直是占满界面",
                    0
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_5,
                    "直接在界面用控件试试",
                    1
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_1,
                    "可以拖了",
                    0
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_4,
                    "啥也没搞，突然又能拖动了。。",
                    0
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_7,
                    "让人怪不好意思的",
                    0
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_6,
                    "为啥一点打字会吃掉一点",
                    0
                )
            )
            msgDataList.add(
                MsgData(
                    R.drawable.img_5,
                    "测试看看 下面的区域如果是一个整体，设置一点的Top内边距",
                    1
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_msg)

        //去掉Activity上面的标题栏
        supportActionBar?.hide()

        //数据初始化
        initMsgData()
        //设置线性布局管理器到控件
        MsgRecyclerView.layoutManager = LinearLayoutManager(this)
        //判断adapter是否已经初始化
        if (!::adapter.isInitialized) {
            //实例化数据适配器，装载数据
            adapter = MsgDataAdapter(msgDataList)
        }
        //为适配器添加缩放动画
        val adapter1 = ScaleInAnimationAdapter(adapter).apply {
            //设置动画时长
            setDuration(500)
        }
        //叠加上面的动画，添加淡出动画
        val adapter2 = AlphaInAnimationAdapter(adapter1).apply {
            setDuration(300)
        }
        //将数据适配器的设置到控件上
        MsgRecyclerView.adapter = adapter2
        MsgRecyclerView.scrollToPosition(msgDataList.size - 1)

        MsgSend.setOnClickListener {
            val text = MsgTextSend.text.toString()
            if (text.isNotEmpty()) {
                val list = adapter.getList()
                if (msgDataList.size != list.size) msgDataList = list as ArrayList<MsgData>
                val msg = MsgData(R.drawable.img_5, text, MsgData.TYPE_RIGHT)
                //添加数据到数据源
                msgDataList.add(msg)
                //通知列表有新的数据，刷新列表
                adapter2.notifyItemInserted(msgDataList.size - 1)
                //将列表定位到最后一行
                MsgRecyclerView.scrollToPosition(msgDataList.size - 1)
                //清空输入框中的内容
                MsgTextSend.setText("")
            }
        }

        rootLayout.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
            if (oldBottom != -1 && oldBottom > bottom) {
                MsgRecyclerView.requestLayout()
                MsgRecyclerView.post { MsgRecyclerView.scrollToPosition(msgDataList.size - 1) }
            }
        }
    }
}