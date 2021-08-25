package com.hyu.dongzi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.Toast
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

        val lv = findViewById<ListView>(R.id.lv_contractList)
        adapter = ContractListAdapter(list)
        lv.adapter = adapter

        getData()

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
