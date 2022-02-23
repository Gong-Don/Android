package com.example.gong_don_android

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface SignUpService {
    @POST("signup")
    @Headers("accept: application/json",
        "content-type: application/json")

    fun requestSignUp(
        @Body jsonparams: SignUp
    ) : Call<SignUpResult>

    companion object {
        private const val BASE_URL = "http://choco-one.iptime.org:11104/api/user/"

        fun create(): SignUpService {
            val gson : Gson = GsonBuilder().setLenient().create();

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(SignUpService::class.java)
        }
    }
}