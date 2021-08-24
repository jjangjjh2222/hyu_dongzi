package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.databinding.ActivityContractBinding
import kotlinx.android.synthetic.main.activity_contract.*

class ContractActivity : AppCompatActivity(){

    val database = Firebase.database

    var auth = Firebase.auth

    val binding by lazy { ActivityContractBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) { //oncreat = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

        val id = intent.getStringExtra("id")
        btn_write.setOnClickListener {
            val intent = Intent(this, Contract2Activity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        database.getReference("board").child(id!!).child("type")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvType.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("deposit")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvDeposit.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("monthly")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvMonthly.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("address")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvAddress.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })
    }
}
