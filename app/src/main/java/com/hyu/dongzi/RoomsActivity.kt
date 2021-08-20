package com.hyu.dongzi

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RoomsActivity : AppCompatActivity() {

    lateinit var adapter : RoomListAdapter
    private lateinit var auth : FirebaseAuth
    val list = mutableListOf<Room>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)


        // 새로고침 버튼
        val reButton = findViewById<Button>(R.id.btn_re)
        reButton.setOnClickListener {
            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)
        }


        // 방등록 버튼
        val addRoomButton = findViewById<Button>(R.id.btn_addRoom)
        addRoomButton.setOnClickListener {

            val intent = Intent(this, AddRoomActivity::class.java)
//            intent.putExtra("uid", auth.currentUser?.uid)
            startActivity(intent)

        }

        adapter = RoomListAdapter(list)

        val lv = findViewById<ListView>(R.id.lv_roomsList)
        lv.adapter = adapter

        lv.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->

            val selectItem = parent.getItemAtPosition(position) as Room
            val intent = Intent(this, RoomInformationActivity::class.java)

            intent.putExtra("deposit", selectItem.deposit)
            intent.putExtra("monthly", selectItem.monthly)
            intent.putExtra("id", selectItem.id)

            startActivity(intent)

        }

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
