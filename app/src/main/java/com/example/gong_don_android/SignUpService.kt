package com.example.gong_don_android

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SignUpService {
    @FormUrlEncoded
    @POST("user/signup")
    fun requestSignUp(
        @Field("name") name:String,
        @Field("email") email:String,
        @Field("password") password:String
    ) : Call<SignUp>
}