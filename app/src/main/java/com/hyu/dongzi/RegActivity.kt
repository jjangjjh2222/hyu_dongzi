package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyu.dongzi.databinding.ActivityRegBinding
import kotlinx.android.synthetic.main.activity_reg.*


class RegActivity : AppCompatActivity() {

    val binding by lazy { ActivityRegBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

        btn_pass.setOnClickListener {

            val intent = Intent(this, RegNextActivity::class.java )
            startActivity(intent)

        }
    }
}