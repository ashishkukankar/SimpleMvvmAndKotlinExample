package com.example.roche.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roche.pojo.User
import com.example.roche.pojo.UserList
import com.example.roche.repository.Repository

class CreateUserModelView : ViewModel() {
    val repository = Repository()

    fun createUser(user:User):MutableLiveData<String>{
        repository.createUser(user)
        return repository.getUserCreateResponse()
    }

    fun getUsers():MutableLiveData<UserList>{
        repository.getUser()
        return repository.getUserList()
    }

}