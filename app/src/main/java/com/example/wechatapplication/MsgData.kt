package com.example.wechatapplication

class MsgData(val ImageID: Int, val Text: String, val type: Int) {
    //用以鉴别发送和接收
    companion object {
        const val TYPE_LEFT = 0
        const val TYPE_RIGHT = 1
    }
}