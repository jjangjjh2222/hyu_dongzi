package com.hyu.dongzi.ChatList

data class ChatListItem(
    val buyerId: String,
    val sellerId: String,
    val itemTitle: String,
    val key: Long
){
    constructor():this("","","",0)
}