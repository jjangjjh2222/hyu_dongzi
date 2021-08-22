package com.hyu.dongzi.chatdetail

data class ChatItem(
    val senderId: String,
    val message: String
){
    constructor(): this("","")
}