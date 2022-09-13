package com.example.newsupdate.Screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.newsupdate.R
import com.example.newsupdate.databinding.ActivityProfileScreenBinding
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import java.util.*
import kotlin.math.log

class Profile_Screen : AppCompatActivity() {


    companion object {
        lateinit var binding: ActivityProfileScreenBinding
        private val RC_SIGN_IN = 1

        var emailid: String? = null
        var emailgoogle: String? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProfileScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this, R.color.offblack)


        binding.signUpPage.setOnClickListener {
            var intent = Intent(this, SignUP_Screen::class.java)
            startActivity(intent)
        }


        binding.signingTxt.setOnClickListener {
            signingAccount(
                binding.emailEdt.text.toString(),
                binding.passwordEdt.text.toString()
            )
            binding.Progressbar.isVisible = true
            binding.signingTxt.isVisible = false
            binding.color1.isVisible = true
        }

        binding.logInWithGoogle.setOnClickListener {
            GoogleSigning()
        }

        binding.anonymousLogin.setOnClickListener {
            anonymousLogIN()
        }

        binding.forgottTxt.setOnClickListener {
            var firebaseAuth = FirebaseAuth.getInstance()

            firebaseAuth.sendPasswordResetEmail("nency.90.dabhi@gmail.com")
                .addOnSuccessListener { res ->
                    Log.e("TAG", "onCreatere: successfully reset Password")
                }.addOnFailureListener { error ->
                Log.e("TAG", "onCreate: ${error.message}")
            }
        }

        binding.backProfile.setOnClickListener {
            onBackPressed()
        }
    }


    fun anonymousLogIN() {

        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signInAnonymously().addOnSuccessListener { res ->
            binding.guestProgress.isVisible = true
            binding.color.isVisible = true
            Handler(Looper.getMainLooper()).postDelayed({
                val mainIntent = Intent(this, Skipped_Page::class.java)
                startActivity(mainIntent)
                finish()
            }, 4000)
        }.addOnFailureListener { error ->
            Log.e("TAG", "anonymousLogIN: ${error.message}")
        }
    }

//    fun FaceBookSignin(){
//        binding.facebookImg.setReadPermissions(Arrays.asList(EMAIL));
//
//        LoginManager.registerCallback(
//            callbackManager,
//            object : FacebookCallback<LoginResult?> {
//                override fun onSuccess(result: LoginResult?) {
//                    FireLoginFB(result?.accessToken?.token.toString())
//                }
//
//                override fun onCancel() {
//                    // App code
//                }
//
//                override fun onError(exception: FacebookException) {
//                    // App code
//                }
//
//            })
//    }

    fun FireLoginFB(token: String) {
        var crd = FacebookAuthProvider.getCredential(token)

        var firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuth.signInWithCredential(crd).addOnSuccessListener { res ->
            var intent = Intent(this, Skipped_Page::class.java)
            startActivity(intent)
        }.addOnFailureListener { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show()
            Log.e("TAG", "FireLoginFB: ${error.message}")
        }
    }


    fun GoogleSigning() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        var googleApiClient =
            GoogleApiClient.Builder(this).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build()

        val intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
        startActivityForResult(intent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            RC_SIGN_IN -> {
                val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data!!)
                var account = result?.signInAccount
                googlefireToken(account?.idToken.toString())
            }
        }
    }

    fun googlefireToken(token: String) {
        var crd = GoogleAuthProvider.getCredential(token, null)
        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signInWithCredential(crd).addOnSuccessListener { res ->
            var intent = Intent(this, Skipped_Page::class.java)
            startActivity(intent)
            finish()
        }.addOnFailureListener { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show()
        }
    }


    fun signingAccount(email: String, password: String) {
        var firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener { res ->

            emailid = email

            Toast.makeText(this, "Login SuccessFully", Toast.LENGTH_LONG).show()

            binding.Progressbar.isVisible = false
            binding.signingTxt.isVisible = true

            var intent = Intent(this, Skipped_Page::class.java)
            startActivity(intent)
            finish()

        }.addOnFailureListener { error ->
            Toast.makeText(this, "${error.message}", Toast.LENGTH_LONG).show()
            binding.signingTxt.isVisible = true
            binding.Progressbar.isVisible = false
        }

    }
}