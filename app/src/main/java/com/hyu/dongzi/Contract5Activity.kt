package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.databinding.ActivityContract5Binding
import kotlinx.android.synthetic.main.activity_contract2.*

class Contract5Activity : AppCompatActivity() {
    val binding by lazy { ActivityContract5Binding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) { //oncreat = 앱이 최초 실행되었을 때 수행한다.

        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.
        val id = intent.getStringExtra("id")

        btn_write.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            Toast.makeText(this, "완료되었습니다. 계약서는 임차인에게 전달됩니다.", Toast.LENGTH_SHORT).show()
            startActivity(intent)


        }
    }
}