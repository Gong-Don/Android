package com.example.gong_don_android

import com.google.gson.annotations.SerializedName

data class SignUp(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("email")
    val email: String? = null,
    @SerializedName("password")
    val password: String? = null
)
data class SignUpResult(
    var result:String? = null
)