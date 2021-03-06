package com.example.gong_don_android.retrofit

import com.example.gong_don_android.*
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("api/user/signin")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestLogIn(
        @Body jsonparams: SignIn
    ) : Call<SignInResult>

    @POST("api/user/signup")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestSignUp(
        @Body jsonparams: SignUp
    ) : Call<SignUpResult>

    @POST("api/user/auth")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestAuth(
        @Body jsonparams: Auth
    ) : Call<AuthResult>

    @POST("api/post")
    @Headers("accept: application/json", "content-type: application/json")
    fun requestPost(
        @Body jsonparams: PostArticle
    ) : Call<PostResult>

    @GET("api/post/all")
    @Headers("accept: application/json", "content-type: application/json")
    fun getPostAll() : Call<List<Post>>;

    @GET("api/tag/all")
    @Headers("accept: application/json", "content-type: application/json")
    fun getTagAll() : Call<HashMap<String,Int>>;

    @GET("api/post/category/{category}")
    @Headers("accept: application/json", "content-type: application/json")
    fun getPostCategory(
       @Path("category") category : String
    ) : Call<List<Post>>;

    @GET("api/post/{postId}")
    @Headers("accept: application/json", "content-type: application/json")
    fun getPost(
        @Path("postId") postId : Int
    ) : Call<PostData>;
}