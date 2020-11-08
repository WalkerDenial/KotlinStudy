package com.wd.kt.coroutine.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.wd.kt.lib.base.vm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : BaseViewModel() {

    val msg = MutableLiveData<String>()

    fun coroutineTest() {
        // 使用 GlobalScope 作用域，通过 launch 开启一段协程
        GlobalScope.launch(Dispatchers.IO) {
            printThreadInfo()
        }
    }

    private fun uiTest1() {
        printThreadInfo("uiTest1()")
    }

    private fun uiTest2() {
        printThreadInfo("uiTest2()")
    }

    private fun uiTest3() {
        printThreadInfo("uiTest3()")
    }

    private suspend fun networkTest1() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTest1()")
    }

    private suspend fun networkTest2() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTest2()")
    }

    private suspend fun networkTest3() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTest3()")
    }

    private fun printThreadInfo(prefix: String = "") {
        Log.i(
            "CoroutineDemo",
            "${if (prefix.isNullOrBlank()) "" else "$prefix, "}current thread is ${Thread.currentThread().name}"
        )
    }


}