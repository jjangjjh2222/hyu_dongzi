package com.hyu.dongzi.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.*
import com.hyu.dongzi.databinding.FragmentUserBinding
import kotlinx.android.synthetic.main.fragment_user.*

class UserFragment : Fragment() {

    private lateinit var binding : FragmentUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        val database = Firebase.database

        var auth = Firebase.auth

        val uid = auth.currentUser?.uid.toString()

        auth = Firebase.auth

        binding = FragmentUserBinding.inflate(inflater, container, false)

        binding.btnContract.setOnClickListener {
            val intent = Intent(this.context, SaveContractActivity::class.java)
            startActivity(intent)
        }

        // 정보수정 버튼
        binding.btnEditInform.setOnClickListener {
            val intent = Intent(this.context, EditInformActivity::class.java)
            startActivity(intent)
        }

        // 학생증 인증 버튼
        binding.certification.setOnClickListener {
            val intent = Intent(this.context, CertificationActivity::class.java)
            startActivity(intent)
        }

        // 학생증 인증 버튼
        binding.btnMyRoom.setOnClickListener {
            val intent = Intent(this.context, MyRoomActivity::class.java)
            startActivity(intent)
        }

        // 로그아웃 버튼
        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }

        // 사용자 이메일 받아오기
        binding.textView4.text = auth.currentUser?.email

        // 사용자 이름 받아오기
        database.getReference("users").child(uid).child("name")
            .addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value.toString()
                binding.textView2.text = value
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        // 사용자 학교 받아오기
        database.getReference("users").child(uid).child("university")
            .addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value.toString()
                binding.textView3.text = value
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })

        // 사용자 학교 받아오기
        database.getReference("users").child(uid).child("university")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.textView3.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        // 사용자 인증 여부 받아오기
        database.getReference("users").child(uid).child("isVerified")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.value == true) {

                        binding.ivVerified.visibility = View.VISIBLE

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        return binding.root
    }
}
