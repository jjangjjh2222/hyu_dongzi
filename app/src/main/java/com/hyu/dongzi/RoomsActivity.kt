package com.hyu.dongzi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hyu.dongzi.databinding.ActivityRoomsBinding

class RoomsActivity : AppCompatActivity() {

    val binding by lazy { ActivityRoomsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.
    }
}