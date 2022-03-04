package com.example.gong_don_android

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.Toast
import com.example.gong_don_android.retrofit.ApiService
import com.example.gong_don_android.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class SignUpActivity : AppCompatActivity() {
    private lateinit var retrofitService : ApiService;
    private lateinit var retrofit : Retrofit
    var mContext = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        var accessToken = ""
        var email = ""

        initRetrofit()

        join_submit.setOnClickListener() {
            var name = input_nickname.text.toString()
            //val isCheckname = namePattern(email)
            var password = input_pw.text.toString()
            val isCheckpw = pwPattern(password)
            email = input_email.text.toString()

            if(isCheckpw)
            {
                val data = SignUp(name, email, password, accessToken)

                retrofitService.requestSignUp(data).enqueue(object : Callback<SignUpResult>{
                    override fun onResponse(call: Call<SignUpResult>, response: Response<SignUpResult>) {
                        Log.d("SIGNUP RESULT", "${response.body()?.code}, ${response.body()?.message}")
                        Log.d("SIGNUP RESULT", accessToken)
                        val intent = Intent(mContext, SignInActivity::class.java)
                        startActivity(intent)
                        Toast.makeText(mContext, "회원가입 성공", Toast.LENGTH_SHORT).show()// 회원가입성공메시지
                        finish()
                    }
                    override fun onFailure(call: Call<SignUpResult>, t: Throwable) {
                        Log.e("SIGNUP", t.message.toString())
                    }
                })
            }
            else
            {
                Toast.makeText(mContext, "비밀번호 형식이 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        auth_btn.setOnClickListener() {
            accessToken = ""
            email = input_email.text.toString()

            val isCheckEmail = emailPattern(email)

            if (isCheckEmail) {
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
                val countDown = object : CountDownTimer(1000 * 10, 1000) {
                    override fun onTick(p0: Long) {
                        // countDownInterval 마다 호출 (여기선 1000ms)
                        auth_btn.isEnabled=false
                    }

                    override fun onFinish() {
                        auth_btn.isEnabled=true
                    }
                }.start()//
            }
            else{
                Toast.makeText(mContext, "이메일 형식이 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun initRetrofit(){
        retrofit = RetrofitClient.create()
        retrofitService = retrofit.create(ApiService::class.java)
    }
    fun emailPattern(email: String): Boolean {//이메일
        val repExp =
            Regex("^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
        return email.matches(repExp)
    }
    fun pwPattern(pw: String): Boolean {//비밀번호
            val repExp = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}\$")
        return pw.matches(repExp)
    }
    fun namePattern(pw: String): Boolean {//닉네임
        val repExp = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,20}\$")
        return pw.matches(repExp)
    }
}