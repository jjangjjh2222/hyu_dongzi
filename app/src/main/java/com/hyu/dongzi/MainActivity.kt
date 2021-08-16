package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hyu.dongzi.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class MainActivity : AppCompatActivity() {

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var auth : FirebaseAuth? =  null
    override fun onCreate(savedInstanceState: Bundle?) { //onCreate = 앱이 최초 실행되었을 때 수행한다.
        super.onCreate(savedInstanceState)
        setContentView(binding.root) //xml 화면 뷰를 연결한다.
        auth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener { // 회원가입 버튼 누르면 화면 넘어가는 기능
            val intent = Intent(this, RegActivity::class.java )
            startActivity(intent)
        }

        btn_login.setOnClickListener { // 로그인 버튼 누르면 화면 넘어가는 기능
            signinEmail()
        }

    }
//    fun signinAndSignup() {
//        auth?.createUserWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
//            ?.addOnCompleteListener {
//            task ->
//                if(task.isSuccessful) {
//                    //Creating a user account
//                }else if(task.exception?.message.isNullOrEmpty()) {
//                    //Show the error message
//                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
//                }else {
//                    //Login if you have account
//                    signinEmail()
//                }
//            }
//        }

    fun signinEmail() {
        auth?.signInWithEmailAndPassword(email_edittext.text.toString(), password_edittext.text.toString())
            ?.addOnCompleteListener {
                    task ->
                if(task.isSuccessful) {
                    //Login
                    moveMainPage(task.result!!.user)
                }else {
                    //Show the error message
                    Toast.makeText(this, task.exception?.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    fun moveMainPage(user:FirebaseUser?){
        if(user != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}

