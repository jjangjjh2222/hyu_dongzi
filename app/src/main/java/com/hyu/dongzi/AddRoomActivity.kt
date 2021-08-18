package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import com.hyu.dongzi.databinding.ActivityAddRoomBinding
import kotlinx.android.synthetic.main.activity_add_room.*
import kotlinx.android.synthetic.main.fragment_user.*

class AddRoomActivity : AppCompatActivity() {
    val binding by lazy { ActivityAddRoomBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.



    }

    fun loadImage(view: View) {
        startActivityForResult(
            Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI),
            0
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data != null) imageView.setImageURI(data?.data)
    }

}