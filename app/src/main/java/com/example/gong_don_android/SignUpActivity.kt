package com.example.gong_don_android

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_sign_up.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SignUpActivity : AppCompatActivity() {
    var signup:SignUp? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        var retrofit = Retrofit.Builder()
            .baseUrl("choco-one.iptime.org:11104/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var signupService: SignUpService = retrofit.create(SignUpService::class.java)

        join_submit.setOnClickListener{
            var name = input_nickname.text.toString()
            var email = input_email.text.toString()
            var password = input_pw.text.toString()

            signupService.requestSignUp(name, email, password).enqueue(object : Callback<SignUp>{
                override fun onFailure(call: Call<SignUp>, t: Throwable) {
                    Log.e("SIGNUP", t.message.toString())
                    var dialog = AlertDialog.Builder(this@SignUpActivity)
                    dialog.setTitle("ERROR")
                    dialog.setMessage("CALL ERROR")
                    dialog.show()
                }

                override fun onResponse(call: Call<SignUp>, response: Response<SignUp>) {
                    signup = response.body()
                    Log.d("SIGNUP", "msg : "+signup?.msg)
                    Log.d("SIGNUP", "code : "+signup?.code)
                    var dialog = AlertDialog.Builder(this@SignUpActivity)
                    dialog.setTitle(signup?.msg)
                    dialog.setMessage(signup?.code)
                    dialog.show()
                }
            })
        }

    }
}