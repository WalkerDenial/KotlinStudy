package com.wd.kt.coroutine.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val msg = MutableLiveData<String>().apply { value = "Test" }

}