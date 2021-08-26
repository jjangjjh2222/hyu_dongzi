package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_contract_detail.*

class ContractDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val database = Firebase.database
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contract_detail)

        val roomId = intent.getStringExtra("roomId")
        val check = intent.getStringExtra("check")
        val contractId = intent.getStringExtra("contractId")

        contractDetailButton.text = check

        if (check == "서명하기") {
            contractDetailButton.isEnabled = true
        }

        // 서명하기 버튼
        contractDetailButton.setOnClickListener {

            database.getReference("contracts")
                .child(contractId.toString())
                .child("isSigned")
                .setValue(true)

            val intent = Intent(this, SaveContractActivity::class.java)
            startActivity(intent)

            Toast.makeText(this, "서명이 완료되었습니다", Toast.LENGTH_SHORT).show()

        }
    }
}
