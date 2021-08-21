package com.hyu.dongzi.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hyu.dongzi.EditInformActivity
import com.hyu.dongzi.MainActivity
import com.hyu.dongzi.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    private lateinit var binding : FragmentUserBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentUserBinding.inflate(inflater, container, false)

        binding.btnEditInform.setOnClickListener {
            val intent = Intent(this.context, EditInformActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogout.setOnClickListener {
            Firebase.auth.signOut()
            Toast.makeText(context, "로그아웃", Toast.LENGTH_SHORT).show()
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }

}
