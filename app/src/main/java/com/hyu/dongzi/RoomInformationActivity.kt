package com.hyu.dongzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RoomInformationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_information)

        val deposit = intent.getStringExtra("deposit")
        val monthly = intent.getStringExtra("monthly")
        val id = intent.getStringExtra("id")

        val depositTextView = findViewById<TextView>(R.id.tv_roomInformDeposit)
        val monthlyTextView = findViewById<TextView>(R.id.tv_roomInformMonthly)
        val idTextView = findViewById<TextView>(R.id.tv_roomInformId)

        depositTextView.text = deposit.toString()
        monthlyTextView.text = monthly.toString()
        idTextView.text = id.toString()

        val storageReference = Firebase.storage.reference.child(id + ".png")
        val image = findViewById<ImageView>(R.id.iv_roomInformImage)

        storageReference.downloadUrl.addOnCompleteListener(OnCompleteListener { task ->
            if (task.isSuccessful) {

                Glide.with(this)
                    .load(task.result)
                    .into(image!!)

            } else {

            }
        })

    }
}
