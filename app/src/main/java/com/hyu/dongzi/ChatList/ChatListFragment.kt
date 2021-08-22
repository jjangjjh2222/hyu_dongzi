package com.hyu.dongzi.ChatList

import android.annotation.SuppressLint

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.R
import com.hyu.dongzi.DBKey.Companion.CHILD_CHAT
import com.hyu.dongzi.DBKey.Companion.DB_USERS
import com.hyu.dongzi.databinding.FragmentChatBinding

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

        initchartListAdapter()

        chatRoomList.clear()

        initChartRecyclerView()

        initChatDB()
    }

    private fun initchartListAdapter() {
        chatListAdapter = ChatListAdapter(onItemClicked = { chatRoom ->
            context?.let {
//                val intent = Intent(it, ChatRoomActivity::class.java)
//                intent.putExtra("chatKey", chatRoom.key) // 인텐트로 키를 전달해서 start
//                startActivity(intent)
            }

        })
    }

    private fun initChartRecyclerView() {
        binding.chatListRecyclerView.adapter = chatListAdapter
        binding.chatListRecyclerView.layoutManager = LinearLayoutManager(context)
    }

    private fun initChatDB() {
        val firebaseUser = auth.currentUser ?: return

        val chatDB =
            Firebase.database.reference.child(DB_USERS).child(firebaseUser.uid).child(CHILD_CHAT)

        // db에 있는 채팅 리스트를 불러와 각각 리스트에 더해준다.
        chatDB.addListenerForSingleValueEvent(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    val model = it.getValue(ChatListItem::class.java)
                    model ?: return
                    chatRoomList.add(model)
                }
                chatListAdapter.submitList(chatRoomList)
                chatListAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    // view가 새로 그려졌을 때;
    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()

        chatListAdapter.notifyDataSetChanged()
    }

}