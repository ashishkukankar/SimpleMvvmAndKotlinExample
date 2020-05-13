package com.example.roche.repository

import androidx.lifecycle.MutableLiveData
import com.example.roche.pojo.User
import com.example.roche.pojo.UserList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class Repository {
    val apiService = ApiBuilder.invoke()
    var userResponse = MutableLiveData<String>()
    var mUserList = MutableLiveData<UserList>()

    fun getUserCreateResponse():MutableLiveData<String>{
        return userResponse
    }

    fun getUserList():MutableLiveData<UserList>{
        return mUserList
    }
    fun createUser(user:User){
        apiService?.createUser(user)?.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                print(t)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    userResponse.value = response.body().toString()
                }
            }

        })

    }


    fun getUser(){
        apiService?.getUsers()?.enqueue(object : retrofit2.Callback<UserList> {
            override fun onFailure(call: Call<UserList>, t: Throwable) {
                print(t)
            }

            override fun onResponse(
                call: Call<UserList>,
                response: Response<UserList>
            ) {
                if(response.isSuccessful) {
                    mUserList.value = response.body()
                }else{
                    print("error message ${response.message()}")
                }
            }

        })
    }
}