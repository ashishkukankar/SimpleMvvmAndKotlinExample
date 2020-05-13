package com.example.roche.repository

import androidx.lifecycle.MutableLiveData
import com.example.roche.pojo.User
import com.example.roche.pojo.UserData
import com.example.roche.pojo.UserList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import javax.security.auth.callback.Callback

class Repository {
    val apiService = ApiBuilder.invoke()
    var userResponse = MutableLiveData<ResponseBody>()
    var mUserList = MutableLiveData<UserList>()
    var mUserData = MutableLiveData<UserData>()

    fun getUserCreateResponse():MutableLiveData<ResponseBody>{
        return userResponse
    }

    fun getUserList():MutableLiveData<UserList>{
        return mUserList
    }

    fun userData():MutableLiveData<UserData>{
        return mUserData
    }

    fun createUser(user:User){
        apiService?.createUser(user)?.enqueue(object : retrofit2.Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                print(t)
            }

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if(response.isSuccessful){
                    userResponse.value = response.body()
                }else{
                    response.message()
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

    fun userDetail(id:String){
        apiService?.userDetails( id)?.enqueue(object : retrofit2.Callback<UserData> {
            override fun onFailure(call: Call<UserData>, t: Throwable) {
                print(t)
            }

            override fun onResponse(
                call: Call<UserData>,
                response: Response<UserData>
            ) {
                if(response.isSuccessful) {
                    mUserData.value = response.body()
                }else{
                    print("error message ${response.message()}")
                }
            }

        })
    }
}