package com.example.wechatapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private val homeDataList = ArrayList<HomeData>()

    private fun initHomeData() {
        repeat(8)
        {
            homeDataList.add(
                HomeData(
                    R.drawable.img_0,
                    "19移动+大数据",
                    "黄冠辉:[厚]",
                    "21:38",
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_1,
                    "黄冠辉",
                    "[可以]",
                    "15:36"
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_2,
                    "黄浩然",
                    "哈哈",
                    "15:03",
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_3,
                    "小斯斯",
                    "iPhone12国行全系列入网：内存4/6GB、电池集体缩水",
                    "15:00",
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_4,
                    "黄凯健",
                    "谢谢",
                    "11:29",
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_5,
                    "幸福猫咖",
                    "太好撸了太划算了！",
                    "昨天",
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_6,
                    "王禧龙",
                    "这是目前上传到github的",
                    "昨天",
                )
            )
            homeDataList.add(
                HomeData(
                    R.drawable.img_7,
                    "李观池",
                    "[动画表情]",
                    "昨天",
                )
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //数据初始化
        initHomeData()
        //设置线性布局管理器
        HomeRecyclerView.layoutManager = LinearLayoutManager(this)
        //实例化数据适配器，装载数据
        val adapter = HomeDataAdapter(homeDataList)
        //为适配器添加缩放动画
        val adapter1 = ScaleInAnimationAdapter(adapter).apply {
            //设置动画时长
            setDuration(500)
            //设置动画重复
            setFirstOnly(false)
        }
        //叠加上面的动画，添加淡出动画
        val adapter2 = AlphaInAnimationAdapter(adapter1).apply {
            setDuration(300)
            setFirstOnly(false)
        }
        //添加默认风格的分割线
        HomeRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        //将数据适配器的设置到控件上
        HomeRecyclerView.adapter = adapter2
    }
}