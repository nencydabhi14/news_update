package com.example.newsupdate.Screen

import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.newsupdate.R
import com.example.newsupdate.Screen.Profile_Screen.Companion.emailid
import com.example.newsupdate.databinding.ActivityEditProfileScreenBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.io.File

class EditProfile_Screen : AppCompatActivity() {

     var uri: Uri? = null
    lateinit var bitmap_img: Bitmap
    lateinit var binding: ActivityEditProfileScreenBinding
    lateinit var username : String
    lateinit var mobile : String

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditProfileScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.grey)

        binding.backProfileEdit.setOnClickListener {
            onBackPressed()
        }

        binding.chooseImage.setOnClickListener {
            chooseImageDialog()
        }

        binding.logOut.setOnClickListener {
            var dialog1 = Dialog(this)
            dialog1.setContentView(R.layout.logout_dialog)
            dialog1.show()

            var yes_txt = dialog1.findViewById<TextView>(R.id.yes_txt)

            yes_txt.setOnClickListener {

                binding.progressLogout.isVisible = true
                binding.logoutgscreen.isVisible = true

                var firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.signOut()
                logoutAccountGoggle()

                Handler(Looper.getMainLooper()).postDelayed({
                    var intent = Intent(this, Profile_Screen::class.java)
                    startActivity(intent)

                    finishAffinity()
                }, 2000)

                dialog1.dismiss()
            }
        }

        binding.doneUser.setOnClickListener {
            username = binding.userNameEdt.text.toString()
            var sharedPref = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            var editor = sharedPref.edit()
            editor.putString("username",username)
            editor.commit()

            Toast.makeText(this, "Save Username", Toast.LENGTH_SHORT).show()
        }

        binding.doneMobile.setOnClickListener {
            mobile = binding.mobileEdt.text.toString()
            var sharedPref = this.getSharedPreferences("MyPref", MODE_PRIVATE)
            var editor = sharedPref.edit()
            editor.putString("mobile", mobile)
            editor.commit()

            Toast.makeText(this, "Save MobileNum", Toast.LENGTH_SHORT).show()

        }
    }

    fun chooseImageDialog() {
        var dialog = BottomSheetDialog(this)
        dialog.setContentView(R.layout.dialog_choose_image)
        dialog.show()

        var gallery_img = dialog.findViewById<ImageView>(R.id.gallery_img)
        var camera_img = dialog.findViewById<ImageView>(R.id.camera_img)


        gallery_img!!.setOnClickListener {
            var intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 100)

            dialog.dismiss()
        }

        camera_img!!.setOnClickListener {

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 200)
            dialog.dismiss()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100) {
            uri = data!!.data!!
            binding.profileImg.setImageURI(uri)

            binding.profileImg.isVisible = true
            binding.circle.isVisible = false
            binding.pf.isVisible = false
        }
        else if (requestCode == 200  ) {
            binding.profileImg.isVisible = true
            bitmap_img = data?.extras!!["data"] as Bitmap
            binding.pf.setImageBitmap(bitmap_img)
            Glide.with(this).load(bitmap_img).placeholder(R.drawable.newslogo).into(binding.profileImg)
            binding.circle.isVisible = false
            binding.pf.isVisible = false
        }

        var sharedPref = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        var editor = sharedPref.edit()
        editor.putString("image", uri.toString())
        editor.commit()


    }



    override fun onResume() {
        super.onResume()

        var sharedPref = this.getSharedPreferences("MyPref", MODE_PRIVATE)
        var image = sharedPref.getString("image", null)
        var username = sharedPref.getString("username", null)
        var mobile = sharedPref.getString("mobile", null)

        Glide.with(this).load(image).into(binding.profileImg)
        binding.userNameEdt.setText(username)
        binding.mobileEdt.setText(mobile)
//        binding.circle.isVisible = false
//        binding.pf.isVisible = false
    }

    fun logoutAccountGoggle(){
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build()
        val googleSignInClient = GoogleSignIn.getClient(this,gso)
        googleSignInClient.signOut()
    }

    fun UploadeUri() {

        var file = File(uri.toString())

        var storage = Firebase.storage
        var storageReference = storage.reference.child("${file.name}")

        var uploadTask = storageReference.putFile(uri!!)

        uploadTask.addOnSuccessListener { snapshot ->

            Toast.makeText(this, "SuccessFull", Toast.LENGTH_SHORT).show()

            getImageFromFirebase()
        }.addOnFailureListener { error ->
            Log.e("TAG", "UploadeUri: ${error.message}")
        }
    }

    fun getImageFromFirebase() {

        var file = File(uri.toString())

        var storage = Firebase.storage
        var storageReference = storage.reference

        storageReference.child("${file.name}").downloadUrl.addOnSuccessListener { uri ->
            Toast.makeText(this, "SuccessFully Get Image", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener { error ->
            Log.e("TAG", "getImageFromFirebase: ${error.message}")
        }
    }

}