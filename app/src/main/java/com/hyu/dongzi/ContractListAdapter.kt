package com.hyu.dongzi

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

class ContractListAdapter(private val List: MutableList<Contract>) : BaseAdapter() {

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

        val database = Firebase.database

        val auth = Firebase.auth

        val uid = auth.currentUser?.uid.toString()


        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.contract_item, parent, false)
        }


        val sellerName = view?.findViewById<TextView>(R.id.tv_sellerName)
        val address = view?.findViewById<TextView>(R.id.tv_contractAddress)
        val buyerName = view?.findViewById<TextView>(R.id.tv_buyerName)


        // 사용자 이름 받아오기
        database.getReference("users").child(uid).child("name")
            .addValueEventListener(object :ValueEventListener{
                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    val name = snapshot.value.toString()

                    if (name == List[position].buyerName || name == List[position].sellerName) {

                        sellerName?.text = List[position].sellerName
                        address?.text = List[position].address
                        buyerName?.text = List[position].buyerName

                    }
                }
            })

        return view!!

    }
}
