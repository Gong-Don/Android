package com.example.gong_don_android.retrofit

import com.example.gong_don_android.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("/user/signin")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestLogIn(
        @Body jsonparams: SignIn
    ) : Call<SignInResult>

    @POST("/user/signup")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestSignUp(
        @Body jsonparams: SignUp
    ) : Call<SignUpResult>

    @POST("/user/auth")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestAuth(
        @Body jsonparams: Auth
    ) : Call<AuthResult>

    @GET("/post/all")
    @Headers("accept: application/json", "content-type: application/json")
    fun getPostAll() : Call<PostList>;

}