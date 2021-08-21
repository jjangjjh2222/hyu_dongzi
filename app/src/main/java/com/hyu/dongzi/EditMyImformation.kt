package com.hyu.dongzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_edit_my_imformation.*



class EditMyImformation : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_my_imformation)

        btn_myInform.setOnClickListener {

            val user = auth.currentUser

            Toast.makeText(this, user?.email.toString(), Toast.LENGTH_SHORT).show()

        }
    }
}

