package com.example.gong_don_android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_log_in.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignInActivity : AppCompatActivity() {
    private lateinit var signinService : ApiService;
    private lateinit var retrofit : Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        initRetrofit()

        login_btn.setOnClickListener{
            var id = input_id.text.toString()
            var password = input_pw.text.toString()
            val data = SignIn(id, password)

            signinService.requestLogIn(data).enqueue(object : Callback<SignInResult> {
                override fun onResponse(call: Call<SignInResult>, response: Response<SignInResult>) {
                    Log.d("SIGNIN RESULT", "${response.body()!!.userId}")

                }
                override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                    Log.e("SIGNIN", t.message.toString())
                }
            })
        }

        signup_btn.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        signinService = retrofit.create(ApiService::class.java)
    }
}