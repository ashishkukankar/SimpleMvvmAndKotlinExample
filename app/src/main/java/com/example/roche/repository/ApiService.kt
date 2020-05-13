package com.example.roche.repository

import com.example.roche.pojo.User
import com.example.roche.pojo.UserList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("users")
    fun createUser(@Body userDetails:User):Call<ResponseBody>

    @GET("users")
    fun  getUsers():Call<UserList>
}
