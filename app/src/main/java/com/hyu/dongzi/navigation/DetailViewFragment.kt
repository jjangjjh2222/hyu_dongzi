package com.hyu.dongzi.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import android.content.Intent
import androidx.viewpager2.widget.ViewPager2
import com.hyu.dongzi.RoomsActivity
import com.hyu.dongzi.databinding.FragmentDetailBinding


class DetailViewFragment : Fragment() {
    // 뷰가 사라질 때 즉 메모리에서 날라갈 때 같이 날리기 위해 따로 뺴두기
    private var fragmentDetailBinding : FragmentDetailBinding? = null

    private val mPager: ViewPager2? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        var view = LayoutInflater.from(activity).inflate(R.layout.fragment_detail, container, false)
//        return view
        val binding = FragmentDetailBinding.inflate(inflater, container, false) // 방 둘러보기 화면으로 넘어가는 기능
        fragmentDetailBinding = binding

        fragmentDetailBinding!!.btnRoom.setOnClickListener {
            val intent = Intent(this.context, RoomsActivity::class.java)
            startActivity(intent)
        }


        return fragmentDetailBinding!!.root
    }

    override fun onDestroyView() {
        fragmentDetailBinding = null
        super.onDestroyView()
    }


}