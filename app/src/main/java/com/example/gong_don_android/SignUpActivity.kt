package com.example.gong_don_android

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.os.Bundle
import android.util.Log
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignUpActivity : AppCompatActivity() {
    private lateinit var retrofitService : ApiService;
    private lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var accessToken = ""
        var email = ""

        initRetrofit()

        join_submit.setOnClickListener() {
            var name = input_nickname.text.toString()
            var password = input_pw.text.toString()
            email = input_email.text.toString()

            val data = SignUp(name, email, password, accessToken)

            retrofitService.requestSignUp(data).enqueue(object : Callback<SignUpResult>{
                override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
                    Log.d("SIGNUP RESULT", "${response.body()?.code}, ${response.body()?.message}")
                }
                override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                    Log.e("SIGNUP", t.message.toString())
                }
            })
        }

        auth_btn.setOnClickListener() {
            accessToken = ""
            email = input_email.text.toString()
            val data = Auth(email)

            retrofitService.requestAuth(data).enqueue(object : Callback<AuthResult>{
                override fun onResponse(call: Call<AuthResult>, response: Response<AuthResult>) {
                    Log.d("AUTH RESULT", "${response.body()?.accessToken}")
                    accessToken = response.body()!!.accessToken.toString()
                }
                override fun onFailure(call: Call<AuthResult>, t: Throwable) {
                    Log.e("AUTH", t.message.toString())
                }
            })
        }
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        retrofitService = retrofit.create(ApiService::class.java)
    }
}