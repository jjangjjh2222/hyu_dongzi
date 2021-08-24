package com.hyu.dongzi

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.provider.MediaStore
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.activity_add_room.*
import java.io.ByteArrayOutputStream

class AddRoomActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    val storage = Firebase.storage

    override fun onCreate(savedInstanceState: Bundle?) {

        auth = Firebase.auth

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_room)

        // 방종류
        val roomType = resources.getStringArray(R.array.roomType)
        val roomTypeAdapter = ArrayAdapter(this, R.layout.list_item, roomType)
        tv_roomType.setAdapter(roomTypeAdapter)

        // 층수
        val roomFloors = resources.getStringArray(R.array.roomFloors)
        val roomFloorsAdapter = ArrayAdapter(this, R.layout.list_item, roomFloors)
        tv_roomFloors.setAdapter(roomFloorsAdapter)

        btn_upload.setOnClickListener {
            val type = findViewById<EditText>(R.id.tv_type)
            val floor = findViewById<EditText>(R.id.tv_floor)
            val deposit = findViewById<EditText>(R.id.et_deposit)
            val monthly = findViewById<EditText>(R.id.et_monthly)
            val address = findViewById<EditText>(R.id.et_address)

            val database = Firebase.database
            val myRef = database.getReference("board")

            val intent = Intent(this, RoomsActivity::class.java)

            val key = myRef.push().key.toString()

            val uid = auth.currentUser?.uid

            myRef.child(key).setValue(
                Room(type.text.toString(),
                    floor.text.toString(),
                    deposit.text.toString(),
                    monthly.text.toString(),
                    address.text.toString(),
                    key,
                    uid.toString())
            )

            Toast.makeText(this, "방 등록중...", Toast.LENGTH_SHORT).show()

            imageUpload(key)

            Handler().postDelayed({startActivity(intent)},2000)



        }

        val uploadImageButton = findViewById<ImageView>(R.id.btn_uploadImage)
        uploadImageButton.setOnClickListener {

            val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            startActivityForResult(gallery, 1)

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && requestCode == 1) {
            findViewById<ImageView>(R.id.btn_uploadImage).setImageURI(data?.data)
        }
    }

    private fun imageUpload(key : String) {

        val imageView = findViewById<ImageView>(R.id.btn_uploadImage)
        val storageRef = storage.reference
        val mountainsRef = storageRef.child(key +".png")


        imageView.isDrawingCacheEnabled = true
        imageView.buildDrawingCache()
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = mountainsRef.putBytes(data)
        uploadTask.addOnFailureListener {

        }.addOnSuccessListener { taskSnapshot ->

        }

    }
}
