package com.hyu.dongzi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class SaveContractActivity : AppCompatActivity(){


    private lateinit var adapter : ContractListAdapter
    private val list = mutableListOf<Contract>()

    val database = Firebase.database
    var auth = Firebase.auth
    val uid = Firebase.auth.currentUser?.uid.toString()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savecontract)

        getData()

        val lv = findViewById<ListView>(R.id.lv_contractList)
        adapter = ContractListAdapter(list)
        lv.adapter = adapter

        // 계약 클릭시 계약 정보로 이동
        lv.onItemClickListener = AdapterView.OnItemClickListener{ parent, view, position, id ->

            val selectItem = parent.getItemAtPosition(position) as Contract
            val intent = Intent(this, ContractDetailActivity::class.java)

            intent.putExtra("roomId", selectItem.roomId)
            intent.putExtra("check", view.findViewById<TextView>(R.id.textView12).text.toString())
            intent.putExtra("contractId", selectItem.contractId)

            startActivity(intent)

        }

    }

    fun getData() {
        val database = Firebase.database
        val myRef = database.getReference("contracts")

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {

                    val item = dataModel.getValue(Contract::class.java)

                    list.add(item!!)

                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("RoomsActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)
    }

}
