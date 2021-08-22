package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyu.dongzi.databinding.ActivityContract5Binding
import kotlinx.android.synthetic.main.activity_contract2.*

class Contract5Activity : AppCompatActivity() {
    val binding by lazy { ActivityContract5Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) { //oncreat = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

//        btn_write.setOnClickListener {
//            val intent = Intent(this, Contract3Activity::class.java)
//            startActivity(intent)
//        }
    }
}