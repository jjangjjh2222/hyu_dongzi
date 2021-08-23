package com.hyu.dongzi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hyu.dongzi.databinding.ActivityContract5Binding
import kotlinx.android.synthetic.main.activity_contract2.*

class Contract5Activity : AppCompatActivity() {
    val binding by lazy { ActivityContract5Binding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) { //oncreat = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

        btn_write.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            Toast.makeText(this, "완료되었습니다. 계약서는 임차인에게 전달됩니다.", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }
    }
}