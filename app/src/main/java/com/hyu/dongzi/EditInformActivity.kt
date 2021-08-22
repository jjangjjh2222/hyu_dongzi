package com.hyu.dongzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class EditInformActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        val database = Firebase.database
        val myRef = database.getReference("users")
        val user = Firebase.auth.currentUser

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_inform)

        val nameEditText = findViewById<EditText>(R.id.et_editName)
        val univEditText = findViewById<EditText>(R.id.et_editUniv)
        val nameButton = findViewById<Button>(R.id.btn_editName)
        val univButton = findViewById<Button>(R.id.btn_editUniv)
        val uid = user?.uid.toString()


        // 이름 수정 버튼
        nameButton.setOnClickListener {
            val editName = nameEditText.text.toString()
            myRef.child(uid).child("name").setValue(editName)
            Toast.makeText(this, "이름 수정 완료", Toast.LENGTH_SHORT).show()
        }

        // 학교 수정 버튼
        univButton.setOnClickListener {
            val editUniv = univEditText.text.toString()
            myRef.child(uid).child("university").setValue(editUniv)
            Toast.makeText(this, "학교 수정 완료", Toast.LENGTH_SHORT).show()
        }

    }
}
