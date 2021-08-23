package com.hyu.dongzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyu.dongzi.databinding.ActivitySavecontractBinding
class SaveContractActivity : AppCompatActivity(){
    val binding by lazy { ActivitySavecontractBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) { //oncreat = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.

    }
}