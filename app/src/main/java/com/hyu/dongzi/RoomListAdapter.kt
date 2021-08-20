package com.hyu.dongzi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import org.w3c.dom.Text

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

        var view = convertView

        if (view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.room_item, parent, false)
        }

        val deposit = view?.findViewById<TextView>(R.id.tv_deposit)
        val monthly = view?.findViewById<TextView>(R.id.tv_monthly)

        val storageReference = Firebase.storage.reference.child(List[position].id + ".png")
        val image = view?.findViewById<ImageView>(R.id.iv_roomImage)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(view!!)
                    .load(task.result)
                    .into(image!!)

            } else {

            }
        })



        deposit!!.text = List[position].deposit
        monthly!!.text = List[position].monthly


        return view!!

    }
}