package com.hyu.dongzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_room_information.*

class RoomInformation : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_information)

        tv_deposit_inform.text = intent.getStringExtra("보증금")
        tv_monthly_inform.text = intent.getStringExtra("월세")
        tv_roomType_inform.text = intent.getStringExtra("방구조")

    }
}