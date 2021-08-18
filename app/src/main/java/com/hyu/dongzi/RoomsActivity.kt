package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.hyu.dongzi.databinding.ActivityRoomsBinding
import kotlinx.android.synthetic.main.activity_add_room.*
import kotlinx.android.synthetic.main.activity_rooms.*
import kotlinx.android.synthetic.main.fragment_user.view.*


class RoomsActivity : AppCompatActivity() {
//    private var uid:String?= ""
    private lateinit var auth : FirebaseAuth
    private lateinit var database : DatabaseReference
    val binding by lazy { ActivityRoomsBinding.inflate(layoutInflater) }


//     listView 에 들어갈 정보들을 Rooms 클래스에 맞춰 roomsList 라는 List 에 담음
    val roomsList = arrayListOf<Rooms>(
//        Rooms(R.drawable.ic_launcher_foreground, "300만원", "30만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "100만원", "35만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "200만원", "30만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "500만원", "40만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "50만원", "30만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "10만원", "50만원", "투룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "150만원", "30만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "250만원", "30만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "30만원", "30만원", "원룸"),
//        Rooms(R.drawable.ic_launcher_foreground, "5만원", "30만원", "원룸")
    )

    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference().child("board")

        ListView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as Rooms
            val image = selectItem.roomImage
            val deposit = selectItem.deposit
            val monthly = selectItem.monthly
            val roomType = selectItem.roomType

            val intent = Intent(this, RoomInformation::class.java)
            intent.putExtra("보증금", deposit)
            intent.putExtra("월세", monthly)
            intent.putExtra("사진", image)
            intent.putExtra("방구조", roomType)
            startActivity(intent)


        }
        val adapter = roomsAdapter(this, roomsList)
        ListView.adapter = adapter

        fab_add_room.setOnClickListener {
            val intent = Intent(this, AddRoomActivity::class.java)
            intent.putExtra("uid", auth.currentUser?.uid)
            startActivity(intent)
        }

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(dataSnapshot: DatabaseError) {
               // 실패했을 때
            }

            override fun onDataChange(dataSnapshot: DatabaseError) {
                for (data in dataSnapshot.child) {

                    val modelResult = data.getValue(Rooms::class.java)
                    title_array.add(modelResult?.title.toString())
                }
                list_adapter.notifyDataSetChanged()
            }
        })


    }
}