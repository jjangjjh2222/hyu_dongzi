package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RoomsActivity : AppCompatActivity() {

    lateinit var adapter : RoomListAdapter

    val list = mutableListOf<Room>()

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        val uid = auth.currentUser?.uid.toString()

        val database = Firebase.database

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)


        // 사용자 학교 받아오기
        database.getReference("users").child(uid).child("university")
            .addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value.toString()
                findViewById<TextView>(R.id.tv_university).text = value
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        // 방등록 버튼
        val addRoomButton = findViewById<FloatingActionButton>(R.id.btn_addRoom)
        addRoomButton.setOnClickListener {
            val intent = Intent(this, AddRoomActivity::class.java)
            startActivity(intent)
        }

        // 지도 버튼
        val mapButton = findViewById<ImageView>(R.id.btn_map)
        mapButton.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }


        adapter = RoomListAdapter(list)

        val lv = findViewById<ListView>(R.id.lv_contractList)

        lv.adapter = adapter

        // 방 클릭시 방 정보로 이동
        lv.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->

            val selectItem = parent.getItemAtPosition(position) as Room
            val intent = Intent(this, RoomInformationActivity::class.java)
            intent.putExtra("type", selectItem.type)
            intent.putExtra("floor", selectItem.floor)
            intent.putExtra("deposit", selectItem.deposit)
            intent.putExtra("monthly", selectItem.monthly)
            intent.putExtra("address", selectItem.address)
            intent.putExtra("explain", selectItem.explain)
            intent.putExtra("id", selectItem.id)

            startActivity(intent)

        }
        // 방 목록 만들기
        getData()
    }

    fun getData() {
        val database = Firebase.database
        val myRef = database.getReference("board")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(Room::class.java)
                    list.add(item!!)

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("RoomsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}
