package com.hyu.dongzi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


// 어뎁터 생성
class roomsAdapter (val context: Context,
                    val roomsList: ArrayList<Rooms> // 정보들이 들어있는 리스트를 받아옴
                    ) : BaseAdapter() {

    // 일단 중요하지 않음
    override fun getCount(): Int {
        return roomsList.size
    }

    // 일단 중요하지 않음
    override fun getItem(position: Int): Any {
        return roomsList[position]
    }

    // 일단 중요하지 않음
    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        // rooms_item.xml 에서 각각 위치에 정보를 넣기 위해 변수로 지정
        val view = LayoutInflater.from(context).inflate(R.layout.rooms_item, null)
        val roomImage = view.findViewById<ImageView>(R.id.iv_roomImage)
        val deposit = view.findViewById<TextView>(R.id.tv_deposit)
        val monthly = view.findViewById<TextView>(R.id.tv_monthly)
        val roomType = view.findViewById<TextView>(R.id.tv_roomType)

        // 받아온 리스트에서 한 줄을 만들기위해 0번째부터 하나하나 꺼내어 room 이라는 변수에 저장
        val room = roomsList[position]

        //위에서 지정했던 xml 파일 위치 변수에 room 에 들어있는 값 할당
        roomImage.setImageResource(room.roomImage)
        deposit.text = room.deposit
        monthly.text = room.monthly
        roomType.text = room.roomType

        return view
    }
}