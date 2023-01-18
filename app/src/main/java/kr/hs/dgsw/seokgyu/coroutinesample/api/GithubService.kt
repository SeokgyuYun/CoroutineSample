package kr.hs.dgsw.seokgyu.coroutinesample.api

import kr.hs.dgsw.seokgyu.coroutinesample.model.UserInfo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{owner}")
    suspend fun getUserInfo(
        @Path("owner") owner: String
    ): UserInfo
}