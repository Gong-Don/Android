package com.example.gong_don_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
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
        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
        val autoLoginEdit = auto.edit()
        var id = auto.getString("id", null)
        var password = auto.getString("password", null)

        initRetrofit()

        if(id!=null&&password!=null)
        {
            val data = SignIn(id, password)
            signinService.requestLogIn(data).enqueue(object : Callback<SignInResult> {
                override fun onResponse(call: Call<SignInResult>, response: Response<SignInResult>) {
                    if(response.body()?.userId!=null)
                    {
                        val intent = Intent(this@SignInActivity, MainActivity::class.java)
                        App.appId=response.body()?.userId.toString().toInt()
                        Log.d("SIGNIN RESULT", "${App.appId}")
                        startActivity(intent)

                    }
                    else
                    {

                    }

                }
                override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                    Log.e("SIGNIN", t.message.toString())
                }
            })


        }
        else
        {
            login_btn.setOnClickListener{
                id = input_id.text.toString()
                password = input_pw.text.toString()
                val data = SignIn(id, password)

                signinService.requestLogIn(data).enqueue(object : Callback<SignInResult> {
                    override fun onResponse(call: Call<SignInResult>, response: Response<SignInResult>) {
                        Log.d("SIGNIN RESULT", "${response.body()?.userId}")
                        App.appId= response.body()?.userId.toString().toIntOrNull()!!
                        val auto = getSharedPreferences("autoLogin", MODE_PRIVATE)
                        val autoLoginEdit = auto.edit()
                        autoLoginEdit.putString("id", id)
                        autoLoginEdit.putString("password", password)
                        autoLoginEdit.apply()
                    }
                    override fun onFailure(call: Call<SignInResult>, t: Throwable) {
                        Log.e("SIGNIN", t.message.toString())
                    }
                })

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
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