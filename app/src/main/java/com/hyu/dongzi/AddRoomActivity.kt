package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class AddRoomActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)

        val uploadButton = findViewById<Button>(R.id.btn_upload)
        uploadButton.setOnClickListener {

            val deposit = findViewById<EditText>(R.id.et_deposit)
            val monthly = findViewById<EditText>(R.id.et_monthly)

            val database = Firebase.database
            val myRef = database.getReference("board")

            myRef.push().setValue(
                Room(deposit.text.toString(),
                    monthly.text.toString())
            )

            val intent = Intent(this, RoomsActivity::class.java)
            startActivity(intent)

        }

    }
}
