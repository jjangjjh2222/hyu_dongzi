package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hyu.dongzi.databinding.ActivityRegBinding
import kotlinx.android.synthetic.main.activity_reg.*


class RegActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)

        btn_pass.setOnClickListener {
            val intent = Intent(this, RegNextActivity::class.java )
            startActivity(intent)
        }
    }
}
