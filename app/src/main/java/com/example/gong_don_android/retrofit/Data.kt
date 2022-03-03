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