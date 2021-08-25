package com.hyu.dongzi

import android.content.Intent
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class MyAdapter(val roomList: ArrayList<Room>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_item, parent, false)
        return MyViewHolder(view).apply {
            itemView.setOnClickListener {

                val curPos = adapterPosition
                val selectItem = roomList[curPos]
               Intent(parent.context, RoomInformationActivity::class.java).apply {
                    putExtra("type", selectItem.type)
                    putExtra("floor", selectItem.floor)
                    putExtra("deposit", selectItem.deposit)
                    putExtra("monthly", selectItem.monthly)
                    putExtra("address", selectItem.address)
                    putExtra("explain", selectItem.explain)
                    putExtra("id", selectItem.id)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.run { parent.context.startActivity(this) }

            }



        }
    }

    override fun onBindViewHolder(holder: MyAdapter.MyViewHolder, position: Int) {

        holder.monthly.text = roomList[position].monthly
        holder.deposit.text = roomList[position].deposit
        holder.roomType.text = roomList[position].type
        holder.roomFloors.text = roomList[position].floor

        val database = Firebase.database

        val storageReference = Firebase.storage.reference.child(roomList[position].id + ".png")

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(holder.itemView)
                    .load(task.result)
                    .into(holder.image)

            }
        })

        // 사용자 인증 여부 받아오기
        database.getReference("users").child(roomList[position].uid).child("isVerified")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == true) {

                        holder.verified.visibility = View.VISIBLE

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

    }

    override fun getItemCount(): Int {
        return roomList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var monthly = itemView.findViewById<TextView>(R.id.tv_monthly)
        val deposit = itemView.findViewById<TextView>(R.id.tv_deposit)
        val roomType = itemView.findViewById<TextView>(R.id.tv_roomType)
        val roomFloors = itemView.findViewById<TextView>(R.id.tv_roomFloors)
        val image = itemView.findViewById<ImageView>(R.id.iv_roomImage)
        val verified = itemView.findViewById<ImageView>(R.id.iv_verifiedItem)


    }

}