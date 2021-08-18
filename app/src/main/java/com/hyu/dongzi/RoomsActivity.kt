package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import com.hyu.dongzi.databinding.ActivityRoomsBinding
import kotlinx.android.synthetic.main.activity_add_room.*
import kotlinx.android.synthetic.main.activity_rooms.*

class RoomsActivity : AppCompatActivity() {
    private var uid:String= ""
    val binding by lazy { ActivityRoomsBinding.inflate(layoutInflater) }


    // listView 에 들어갈 정보들을 Rooms 클래스에 맞춰 roomsList 라는 List 에 담음
    val roomsList = arrayListOf<Rooms>(
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸")
    )

    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

        if(intent.hasExtra("uid")){

            uid = intent.getStringExtra("uid")
        }

        val adapter = roomsAdapter(this, roomsList)
        ListView.adapter = adapter

        fab_add_room.setOnClickListener {
            val intent = Intent(this, AddRoomActivity::class.java)
            startActivity(intent)
        }

        btn_addRoom.setOnClickListener {

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference()

            val dataInput = Rooms(





            )
            myRef.child(uid).push().setValue(dataInput)

        }
    }
}