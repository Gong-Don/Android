package com.example.gong_don_android

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {
    val signupService = SignUpService.create();
    val signup:SignUpResult? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        join_submit.setOnClickListener() {
            var name = input_nickname.text.toString()
            var email = input_email.text.toString()
            var password = input_pw.text.toString()
            val data = SignUp(name, email, password)

            signupService.requestSignUp(data).enqueue(object : Callback<SignUpResult>{
                override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
                    Log.d("result", response.toString())
                }
                override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                    Log.e("SIGNUP", t.message.toString())
                }
            })
        }
    }
}