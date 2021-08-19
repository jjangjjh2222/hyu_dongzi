package com.hyu.dongzi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class RoomListAdapter (val List : MutableList<Room>) : BaseAdapter() {
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

        var view  = convertView

        if(view == null) {
            view = LayoutInflater.from(parent?.context).inflate(R.layout.room_item, parent, false)
        }

        val deposit = view?.findViewById<TextView>(R.id.tv_deposit)
        val monthly = view?.findViewById<TextView>(R.id.tv_monthly)

        deposit!!.text = List[position].deposit
        monthly!!.text = List[position].monthly

        return view!!

    }
}