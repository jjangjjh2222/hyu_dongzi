package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_reg.*


class RegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        checkBox.setOnClickListener {

            if (checkBox.isChecked) {
                checkBox2.isChecked = true
                checkBox3.isChecked = true
                checkBox4.isChecked = true
            } else {
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox4.isChecked = false
            }

        }

        contractDetailButton.setOnClickListener {
            val intent = Intent(this, RegNextActivity::class.java )
            startActivity(intent)
        }

    }

}
