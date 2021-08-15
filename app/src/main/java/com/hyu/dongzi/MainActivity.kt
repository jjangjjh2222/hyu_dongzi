package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyu.dongzi.databinding.ActivityMainBinding
import com.hyu.dongzi.navigation.DetailViewFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

        btn_register.setOnClickListener { // 회원가입 버튼 누르면 화면 넘어가는 기능

            val intent = Intent(this, RegActivity::class.java )
            startActivity(intent)

        }

        btn_login.setOnClickListener { // 로그인 버튼 누르면 화면 넘어가는 기능

            val intent = Intent(this, HomeActivity::class.java )
            startActivity(intent)

        }
    }
}
