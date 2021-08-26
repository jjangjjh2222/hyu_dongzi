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
        val text = view?.findViewById<TextView>(R.id.textView12)

        // sellerName 구해서 setText
        database.getReference("users").child(List[position].sellerUid).child("name")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    sellerName?.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        // buyerName 구해서 setText
        database.getReference("users").child(List[position].buyerUid).child("name")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    buyerName?.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        // 매물 주소 구해서 setText
        database.getReference("board").child(List[position].roomId).child("address")
            .addValueEventListener(object :ValueEventListener{

                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    address?.text = value
                }

            })

        // 매물 주소 구해서 setText
        database.getReference("contracts").child(List[position].contractId).child("isSigned")
            .addValueEventListener(object :ValueEventListener{

                override fun onCancelled(error: DatabaseError) {}
                override fun onDataChange(snapshot: DataSnapshot) {
                    val isSigned = snapshot.value

                    if (isSigned as Boolean){

                        text?.text = "서명완료"

                    } else {

                        if (uid == List[position].buyerUid) {

                            text?.text = "서명대기"

                        } else {

                            text?.text = "서명하기"

                        }

                    }

                }

            })

        return view!!
    }
}
