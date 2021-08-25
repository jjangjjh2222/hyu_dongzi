package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.databinding.ActivityContract4Binding
import kotlinx.android.synthetic.main.activity_contract5.*

class Contract4Activity : AppCompatActivity() {
    val binding by lazy { ActivityContract4Binding.inflate(layoutInflater) }

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
    }
}