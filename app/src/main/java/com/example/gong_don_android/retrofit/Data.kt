package com.example.gong_don_android

import com.google.gson.annotations.SerializedName

data class SignIn(
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null
)
data class SignInResult(
    @SerializedName("userId")
    var userId:String? = null
)

data class SignUp(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null,
    @SerializedName("tokenId")
    val tokenId: String? = null
)
data class SignUpResult(
    @SerializedName("code")
    var code:String? = null,
    @SerializedName("message")
    var message:String? = null
)


data class Auth(
    @SerializedName("email")
    val email: String? = null
)
data class AuthResult(
    @SerializedName("accessToken")
    var accessToken:String? = null
)

data class PostList(
    val post: ArrayList<Post>
)

data class Post(
    @SerializedName("category")
    val category: Category,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("likeCnt")
    val likeCnt: Boolean,
    @SerializedName("matchingStatus")
    val matchingStatus: Boolean,
    @SerializedName("postId")
    val postId: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("wrtId")
    val wrtId: Int,
    @SerializedName("wrtName")
    val wrtName: String
)
enum class Category{
    DESIGN, IT, MEDIA, STUDY
}

data class WorkData(
    val name : String,
    val context : String,
    val date : String
)
data class PostData(
    val title : String,
    val context : String,
    val status : String
)