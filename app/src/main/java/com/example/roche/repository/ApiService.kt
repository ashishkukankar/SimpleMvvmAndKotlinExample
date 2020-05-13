package com.example.roche.repository

import com.example.roche.pojo.User
import com.example.roche.pojo.UserData
import com.example.roche.pojo.UserList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("users/")
    fun createUser(@Body userDetails:User):Call<ResponseBody>

    @GET("users/")
    fun  getUsers():Call<UserList>

    @GET("users/{id}")
    fun  userDetails(@Path("id")  id:String):Call<UserData>

}
