package com.hyu.dongzi.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import com.hyu.dongzi.RoomsActivity
import com.hyu.dongzi.databinding.FragmentDetailBinding


class DetailViewFragment : Fragment() {

    private lateinit var binding : FragmentDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentDetailBinding.inflate(inflater, container, false)

        binding.btnRoom.setOnClickListener {
            val intent = Intent(this.context, RoomsActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}
