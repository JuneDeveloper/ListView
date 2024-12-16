package com.example.listview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    val userList: MutableLiveData<MutableList<User>> = MutableLiveData(mutableListOf())
}