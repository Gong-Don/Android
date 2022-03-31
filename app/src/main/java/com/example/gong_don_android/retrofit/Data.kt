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

data class Post(
    @SerializedName("category")
    val category: Category,
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("likeCnt")
    val likeCnt: Int,
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
data class PostArticle(
    @SerializedName("category")
    val category: String,
    @SerializedName("content")
    val content: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("wrtId")
    val wrtId: Int
)
data class PostResult(
    @SerializedName("code")
    var code:String? = null,
    @SerializedName("message")
    var message:String? = null
)
enum class Category(val cate: String){
    ALL("전체"),
    DESIGN("디자인"),
    IT("IT"),
    MEDIA("미디어"),
    TRANSLATION("번역"),
    DOCUMENT("문서"),
    STUDY("스터디")
}

data class WorkData(
    val name : String,
    val context : String,
    val date : String
)

data class PostData(
    val category : Category,
    val content : String,
    val date : String,
    val likeCnt: Int,
    val price: Int,
    val tags: ArrayList<String>,
    val title: String,
    val wrtId: Int,
    val wrtName: String
)

class likeBtnData(
    var id: Int,
    var checked: Boolean
)