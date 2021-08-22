package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.core.view.View
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.hyu.dongzi.ChatList.ChatListFragment
import com.hyu.dongzi.ChatList.ChatListItem
import com.hyu.dongzi.DBKey.Companion.CHILD_CHAT
import com.hyu.dongzi.DBKey.Companion.DB_ARTICLES
import com.hyu.dongzi.DBKey.Companion.DB_USERS
import kotlinx.android.synthetic.main.room_item.*

class RoomInformationActivity : AppCompatActivity() {

    private lateinit var articleDB: DatabaseReference
    private lateinit var userDB: DatabaseReference
    private lateinit var roomListAdapter: RoomListAdapter


    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_information)


        val deposit = intent.getStringExtra("deposit")
        val monthly = intent.getStringExtra("monthly")
        val id = intent.getStringExtra("id")

        val depositTextView = findViewById<TextView>(R.id.tv_roomInformDeposit)
        val monthlyTextView = findViewById<TextView>(R.id.tv_roomInformMonthly)
        val idTextView = findViewById<TextView>(R.id.tv_roomInformId)

        depositTextView.text = deposit.toString()
        monthlyTextView.text = monthly.toString()
        idTextView.text = id.toString()

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


//        val lv = findViewById<ListView>(R.id.lv_roomsList)
//        lv.adapter = adapter
//
//        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
//            if (auth.currentUser != null) {
////                // 로그인 상태;
////                // 채팅방 생성
//                val selectItem = parent.getItemAtPosition(position) as Room
//                val intent = Intent(this, ChatListFragment::class.java)
//
//                intent.putExtra("buyerId", auth.currentUser?.uid.toString())
//                intent.putExtra("sellerId", selectItem.uid)
//                intent.putExtra("id", selectItem.id)
//
//
//
//                startActivity(intent)
//
//
//            }
//        }


//    private fun initArticleAdapter(view: View) {
//        roomListAdapter = RoomListAdapter(onItemClicked = { Room ->
//            if(auth.currentUser != null){
//                // 로그인 상태;
//                if(auth.currentUser?.uid != Room.uid){
//                    // 채팅방 생성
//                    val chatRoom = ChatListItem(
//                        buyerId = auth.currentUser?.uid.toString(),
//                        sellerId = Room.uid,
//                        key = System.currentTimeMillis()
//                    )
//
//                    userDB.child(auth.currentUser!!.uid) // 계속 워닝 떠서 !! 처리;
//                        .child(CHILD_CHAT)
//                        .push()
//                        .setValue(chatRoom)
//
//                    userDB.child(Room.sellerId)
//                        .child(CHILD_CHAT)
//                        .push()
//                        .setValue(chatRoom)
//
//                    Snackbar.make(view, "채팅방이 생성되었습니다. 채팅탭에서 확인해주세요.", Snackbar.LENGTH_LONG).show()
//
//                }else{
//                    // 내가 올린 아이템 일때
//                    Snackbar.make(view, "내가 올린 아이템입니다.", Snackbar.LENGTH_LONG).show()
//                }
//            }else{
//                // 로그아웃 상태;
//                Snackbar.make(view, "로그인 후 사용해주세요", Snackbar.LENGTH_LONG).show()
//            }
//
//
//        })
//    }

//    private fun initDB() {
//        articleDB = Firebase.database.reference.child(DB_ARTICLES) // 디비 가져오기;
//        userDB = Firebase.database.reference.child(DB_USERS)
//    }
    }
}

