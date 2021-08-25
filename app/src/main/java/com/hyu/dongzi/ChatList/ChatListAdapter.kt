package com.hyu.dongzi.ChatList


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
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

        val database = Firebase.database


        val auth = Firebase.auth

        val uid = auth.currentUser?.uid.toString()

        var view = convertView

        val sellerId = view?.findViewById<TextView>(R.id.sellerId)

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.item_chatlist, parent, false)
        }

        database.getReference("chatlist").child(uid).child("sellerId")
            .addValueEventListener(object :ValueEventListener{
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {

                    sellerId?.text = List[position].sellerId

                    }
                })

        return view!!

    }

}