package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.databinding.ActivityContract5Binding
import kotlinx.android.synthetic.main.activity_contract2.*
import kotlinx.android.synthetic.main.activity_contract2.btn_write
import kotlinx.android.synthetic.main.activity_contract5.*

class Contract5Activity : AppCompatActivity() {

    val binding by lazy { ActivityContract5Binding.inflate(layoutInflater) }

    val database = Firebase.database

    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id").toString()

        val uid = auth.currentUser?.uid.toString()

        val key = database.getReference("contracts").push().key.toString()

        btn_write.setOnClickListener {

            val intent = Intent(this, HomeActivity::class.java)

            // 등록자 uid 받아오기
            database.getReference("board").child(id).child("uid")
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val sellerUid = snapshot.value.toString()

                        // 등록자 이름 받아오기
                        database.getReference("users").child(sellerUid).child("name")
                            .addValueEventListener(object :ValueEventListener{
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    val sellerName = snapshot.value.toString()

                                    database.getReference("contracts")
                                        .child(key)
                                        .child("sellerName")
                                        .setValue(sellerName)

                                }
                                override fun onCancelled(error: DatabaseError) {
                                }
                            })

                        // sellerUid 에 계약 id 등록하기
                        database.getReference("users").child(sellerUid).child("contracts")
                            .setValue(key)
                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })

            // 게시글 주소 받아오기
            database.getReference("board").child(id).child("address")
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val address = snapshot.value.toString()

                        database.getReference("contracts")
                            .child(key)
                            .child("address")
                            .setValue(address)

                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })

            // 사용자 이름 받아오기
            database.getReference("users").child(uid).child("name")
                .addValueEventListener(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val buyerName = snapshot.value.toString()

                        database.getReference("contracts")
                            .child(key)
                            .child("buyerName")
                            .setValue(buyerName)

                    }
                    override fun onCancelled(error: DatabaseError) {
                    }
                })

            // buyerUid 에 계약 id 등록하기
            database.getReference("users")
                .child(uid).child("contracts")
                .setValue(key)

            Toast.makeText(this, "완료되었습니다. 계약서는 임차인에게 전달됩니다.", Toast.LENGTH_SHORT).show()
            startActivity(intent)


        }
    }
}
