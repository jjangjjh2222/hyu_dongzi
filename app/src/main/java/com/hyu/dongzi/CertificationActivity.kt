package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.navigation.UserFragment
import kotlinx.android.synthetic.main.activity_certification.*

class CertificationActivity : AppCompatActivity() {

    var auth = Firebase.auth
    val uid = auth.currentUser?.uid.toString()
    val database = Firebase.database
    val myRef = database.getReference("users").child(uid).child("isVerified")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_certification)

        btn_verify.setOnClickListener {

            myRef.setValue(true)

            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "인증이 완료되었습니다", Toast.LENGTH_SHORT).show()

        }
    }
}