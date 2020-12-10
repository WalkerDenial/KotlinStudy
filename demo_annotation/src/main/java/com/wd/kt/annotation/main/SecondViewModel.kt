package com.wd.kt.annotation.main

import androidx.lifecycle.MutableLiveData
import com.wd.kt.lib.base.vm.BaseViewModel

class SecondViewModel : BaseViewModel() {

    /**
     * 界面展示数据
     */
    val name = MutableLiveData<String>()

    /**
     * 界面展示数据
     */
    val age = MutableLiveData<Int>()

}