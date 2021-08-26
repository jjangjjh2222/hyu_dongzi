package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.hyu.dongzi.ChatList.ChatListItem
import com.hyu.dongzi.chatdetail.ChatRoomActivity
import kotlinx.android.synthetic.main.activity_room_information.*


class RoomInformationActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_information)

        val type = intent.getStringExtra("type")
        val floor = intent.getStringExtra("floor")
        val deposit = intent.getStringExtra("deposit")
        val monthly = intent.getStringExtra("monthly")
        val address = intent.getStringExtra("address")
        val explain = intent.getStringExtra("explain")
        val id = intent.getStringExtra("id")

        val typeTextView = findViewById<TextView>(R.id.tv_roomInformtype)
        val floorTextView = findViewById<TextView>(R.id.tv_roomInformfloor)
        val depositTextView = findViewById<TextView>(R.id.tv_roomInformDeposit)
        val monthlyTextView = findViewById<TextView>(R.id.tv_roomInformMonthly)
        val addressTextView = findViewById<TextView>(R.id.tv_roomInformAddress)
        val explainTextView = findViewById<TextView>(R.id.tv_roomInformexplain)

        typeTextView.text = type.toString()
        floorTextView.text = floor.toString()
        depositTextView.text = deposit.toString()
        monthlyTextView.text = monthly.toString()
        addressTextView.text = address.toString()
        explainTextView.text = explain.toString()

        val storageReference = Firebase.storage.reference.child(id + ".png")
        val image = findViewById<ImageView>(R.id.iv_roomInformImage)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(image!!)

            }
        })

        btn_contract.setOnClickListener {
            val intent = Intent(this, ContractActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        btn_chat.setOnClickListener {


            val database = Firebase.database
            val myRef = database.getReference("chatlist")

            val intent = Intent(this, ChatRoomActivity::class.java)

            val key = myRef.push().key.toString()


            myRef.child(key).setValue(
                ChatListItem(

                    roomId = id!!


                )
            )
            startActivity(intent)
        }
    }


}


