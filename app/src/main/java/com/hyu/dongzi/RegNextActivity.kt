package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_reg_next.*

class RegNextActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    val storage = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg_next)

        btn_join.setOnClickListener {

            val email = findViewById<EditText>(R.id.et_regEmail)
            val password = findViewById<EditText>(R.id.et_regPassword)
            val database = Firebase.database
            val myRef = database.getReference("users")
            val name = findViewById<EditText>(R.id.et_name)
            val university = findViewById<EditText>(R.id.et_university)

            auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {

                        val uid = auth.currentUser?.uid.toString()

                        myRef.child(uid).setValue(
                            UserProfile(name.text.toString(),  university.text.toString())
                        )

                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)

                    } else {

                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()

                    }
                }

        }

    }
}
