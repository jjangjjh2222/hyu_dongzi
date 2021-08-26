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

            // 매물 Id 등록하기
            database.getReference("contracts")
                .child(key)
                .child("roomId")
                .setValue(id)

            // 게약 Id 등록하기
            database.getReference("contracts")
                .child(key)
                .child("contractId")
                .setValue(key)

            // buyerUid 등록하기
            database.getReference("contracts")
                .child(key)
                .child("buyerUid")
                .setValue(uid)

            // isSigned 등록하기
            database.getReference("contracts")
                .child(key)
                .child("isSigned")
                .setValue(false)

            // sellerUid 등록하기
            database.getReference("board").child(id).child("uid")
                .addValueEventListener(object :ValueEventListener{

                    override fun onCancelled(error: DatabaseError) {}
                    override fun onDataChange(snapshot: DataSnapshot) {

                        val sellerUid = snapshot.value.toString()

                        database.getReference("contracts")
                            .child(key)
                            .child("sellerUid")
                            .setValue(sellerUid)

                    }

                })

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "완료되었습니다. 계약서는 임차인에게 전달됩니다.", Toast.LENGTH_SHORT).show()

        }
    }
}
