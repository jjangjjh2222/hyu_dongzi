package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.hyu.dongzi.databinding.ActivityContract4Binding
import kotlinx.android.synthetic.main.activity_contract5.*

class Contract4Activity : AppCompatActivity() {
    val binding by lazy { ActivityContract4Binding.inflate(layoutInflater) }

    val database = Firebase.database
    val auth = Firebase.auth

    override fun onCreate(savedInstanceState: Bundle?) { //oncreat = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.
        val id = intent.getStringExtra("id")

        val uid = auth.currentUser?.uid.toString()
 
        btn_write.setOnClickListener {
            val intent = Intent(this, Contract5Activity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        database.getReference("board").child(id!!).child("type")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvContract4RoomType.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("floor")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvContract4Floors.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("deposit")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvContract4Deposit.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("monthly")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvContract4Monthly.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        database.getReference("board").child(id).child("address")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val value = snapshot.value.toString()
                    binding.tvContract4Adrees.text = value
                }
                override fun onCancelled(error: DatabaseError) {
                }
            })

        val storageReference = Firebase.storage.reference.child(id.toString() + ".png")
        val image = findViewById<ImageView>(R.id.iv_contract4Image)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(image!!)

            }
        })

    }
}