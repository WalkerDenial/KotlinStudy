package com.wd.kt.annotation.main

import androidx.lifecycle.MutableLiveData
import com.wd.kt.lib.base.vm.BaseViewModel

class MainViewModel : BaseViewModel() {

    /**
     * 界面展示数据
     */
    val msg = MutableLiveData<String>()

}