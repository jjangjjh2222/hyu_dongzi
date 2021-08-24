package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.hyu.dongzi.ChatList.ChatListAdapter
import com.hyu.dongzi.chatdetail.ChatRoomActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_room_information.*
import kotlinx.android.synthetic.main.item_chatlist.*
import kotlinx.android.synthetic.main.room_item.*

class RoomInformationActivity : AppCompatActivity() {
    private lateinit var userDB: DatabaseReference
    lateinit var chatListAdapter: ChatListAdapter
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_information)

        val type = intent.getStringExtra("type")
        val floor = intent.getStringExtra("floor")
        val deposit = intent.getStringExtra("deposit")
        val monthly = intent.getStringExtra("monthly")
        val address = intent.getStringExtra("address")
        val id = intent.getStringExtra("id")

        val typeTextView = findViewById<TextView>(R.id.tv_roomInformtype)
        val floorTextView = findViewById<TextView>(R.id.tv_roomInformfloor)
        val depositTextView = findViewById<TextView>(R.id.tv_roomInformDeposit)
        val monthlyTextView = findViewById<TextView>(R.id.tv_roomInformMonthly)
        val addressTextView = findViewById<TextView>(R.id.tv_roomInformAddress)

        typeTextView.text = type.toString()
        floorTextView.text = floor.toString()
        depositTextView.text = deposit.toString()
        monthlyTextView.text = monthly.toString()
        addressTextView.text = address.toString()

        val storageReference = Firebase.storage.reference.child(id + ".png")
        val image = findViewById<ImageView>(R.id.iv_roomInformImage)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(image!!)

            } else {

            }
        })
        btn_chat.setOnClickListener {
            val intent = Intent(this, ChatRoomActivity::class.java)
            startActivity(intent)
        }

        btn_contract.setOnClickListener {
            val intent = Intent(this, ContractActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

//        btn_chat.setOnClickListener {
//            val lc = findViewById<RecyclerView>(R.id.chatListRecyclerView)
//            lc.adapter = chatListAdapter
//
//            lc.setOnClickListener {
//                AdapterView.OnItemClickListener { parent, view, position, id ->
//
//
//                    val selectItem = parent.getItemAtPosition(position) as ChatListItem
//
//                    userDB.child(auth.currentUser!!.uid) // 계속 워닝 떠서 !! 처리;
//                        .child(CHILD_CHAT)
//                        .push()
//                        .setValue(chatRoomTitleTextView)
//
//
//
//                    userDB = Firebase.database.reference.child(DB_USERS)

//                    val intent = Intent(this, ChatRoomActivity::class.java)
//
//                    intent.putExtra("buyerId", auth.currentUser?.uid.toString())
//                    intent.putExtra("sellerId", selectItem.sellerId)
//
//                    startActivity(intent)

//                }
//            }
//        }
    }

}


