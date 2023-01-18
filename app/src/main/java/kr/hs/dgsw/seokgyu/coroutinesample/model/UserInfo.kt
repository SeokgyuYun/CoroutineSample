package kr.hs.dgsw.seokgyu.coroutinesample.model

import com.google.gson.annotations.SerializedName

data class UserInfo(
    @SerializedName("login")
    val userId: String,
    val followers: Int,
    val following: Int,
)
