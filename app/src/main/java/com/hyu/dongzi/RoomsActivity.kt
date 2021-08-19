package com.hyu.dongzi

import android.content.Intent
import android.graphics.ColorSpace
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RoomsActivity : AppCompatActivity() {

    lateinit var adapter : RoomListAdapter

    val list = mutableListOf<Room>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        val addRoomButton = findViewById<Button>(R.id.btn_addRoom)
        addRoomButton.setOnClickListener {

            val intent = Intent(this, AddRoomActivity::class.java)
            startActivity(intent)

        }

        adapter = RoomListAdapter(list)

        val lv = findViewById<ListView>(R.id.lv_roomsList)
        lv.adapter = adapter

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
                // Getting Post failed, log a message
                Log.w("RoomsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)

    }
}
