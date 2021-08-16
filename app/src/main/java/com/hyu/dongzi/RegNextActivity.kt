package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.hyu.dongzi.databinding.ActivityRegNextBinding
import kotlinx.android.synthetic.main.activity_main.email_edittext
import kotlinx.android.synthetic.main.activity_main.password_edittext
import kotlinx.android.synthetic.main.activity_reg_next.*

class RegNextActivity : AppCompatActivity() {

    val binding by lazy { ActivityRegNextBinding.inflate(layoutInflater) }
    var auth : FirebaseAuth? =  null
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.
        auth = FirebaseAuth.getInstance()

        btn_phone.setOnClickListener { // 로그인 버튼 누르면 화면 넘어가는 기능
            signinAndSignup()
        }
    }

    fun signinAndSignup() {
        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    //Creating a user account
                    moveMainPage(task.result?.user)
                    Toast.makeText(this, "회원가입 되었습니다 !!", Toast.LENGTH_LONG).show()
                }else {
                    //Show the error message
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }

            }
    }

    fun moveMainPage(user: FirebaseUser?){
        if(user != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}


