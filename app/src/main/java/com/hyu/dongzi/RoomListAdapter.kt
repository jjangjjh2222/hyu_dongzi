package com.hyu.dongzi

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RoomListAdapter(val List: MutableList<Room>) : BaseAdapter() {
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

        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.room_item, parent, false)
        }

        val deposit = view?.findViewById<TextView>(R.id.tv_deposit)
        val monthly = view?.findViewById<TextView>(R.id.tv_monthly)
        val roomType = view?.findViewById<TextView>(R.id.tv_roomType)
        val roomFloors = view?.findViewById<TextView>(R.id.tv_roomFloors)
        val verified = view?.findViewById<ImageView>(R.id.iv_verifiedItem)

        val storageReference = Firebase.storage.reference.child(List[position].id + ".png")
        val image = view?.findViewById<ImageView>(R.id.iv_roomImage)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(view!!)
                    .load(task.result)
                    .into(image!!)

            }
        })

        deposit!!.text = List[position].deposit
        monthly!!.text = List[position].monthly
        roomType!!.text = List[position].type
        roomFloors!!.text = List[position].floor

        // 사용자 인증 여부 받아오기
        database.getReference("users").child(List[position].uid).child("isVerified")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == true) {

                        verified?.visibility = View.VISIBLE

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        return view!!

    }
}
