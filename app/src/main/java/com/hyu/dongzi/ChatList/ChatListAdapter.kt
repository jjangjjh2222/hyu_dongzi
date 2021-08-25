package com.hyu.dongzi.ChatList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hyu.dongzi.R

class ChatListAdapter(val List: MutableList<ChatListItem>) : BaseAdapter() {

    override fun getCount(): Int {
        return List.count()
    }

    override fun getItem(position: Int): Any {
        return List[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {



        var view = convertView



        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_chatlist, parent, false)
        }

        val sellerId = view?.findViewById<TextView>(R.id.sellerId)
        sellerId?.text = List[position].sellerId

        return view!!

    }

}