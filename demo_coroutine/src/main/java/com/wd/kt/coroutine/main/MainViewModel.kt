package com.wd.kt.coroutine.main

import androidx.lifecycle.MutableLiveData
import com.wd.kt.lib.base.vm.BaseViewModel

class MainViewModel : BaseViewModel() {

    val msg = MutableLiveData<String>().apply { value = "Coroutine Test" }

}