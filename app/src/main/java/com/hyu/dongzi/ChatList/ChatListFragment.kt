package com.hyu.dongzi.ChatList


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.R
import com.hyu.dongzi.Room
import com.hyu.dongzi.chatdetail.ChatRoomActivity
import com.hyu.dongzi.databinding.FragmentChatBinding
import kotlinx.android.synthetic.*

class ChatListFragment : Fragment(R.layout.fragment_chat) {

    private lateinit var binding: FragmentChatBinding
    private lateinit var chatListAdapter: ChatListAdapter
    private val chatRoomList = mutableListOf<ChatListItem>()

    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentChatBinding = FragmentChatBinding.bind(view)

        binding = fragmentChatBinding


        chatRoomList.clear()

        chatListAdapter = ChatListAdapter(chatRoomList)

        val lv = binding.lvChatlist


        lv.adapter = chatListAdapter

        // 방 클릭시 방 정보로 이동
        lv.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

            val intent = Intent(this.context, ChatRoomActivity::class.java)

            startActivity(intent)
        }

        getData()

    }
    fun getData() {
        val database = Firebase.database
        val myRef = database.getReference("chatlist")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(ChatListItem::class.java)
                    chatRoomList.add(item!!)

                }
                chatListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("ChatListFragment", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }
}