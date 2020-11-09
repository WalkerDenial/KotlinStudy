package com.wd.kt.coroutine.main

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wd.kt.lib.base.vm.BaseViewModel
import com.wd.kt.net.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainViewModel : BaseViewModel() {

    val msg = MutableLiveData<String>()

    /**
     * 1. 使用 GlobalScope 作用域
     * 2. 通过 launch 开启一段协程
     */
    fun globalCoroutineTest() {
        GlobalScope.launch(Dispatchers.IO) {
            printThreadInfo()
        }
    }

    /**
     * 模拟网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * 线程写法
     */
    fun <A : ComponentActivity> threadTest(activity: A?) {
        thread {
            networkTest1()
            activity?.runOnUiThread {
                uiTest1()
                thread {
                    networkTest2()
                    activity?.runOnUiThread {
                        uiTest2()
                        thread {
                            networkTest3()
                            activity?.runOnUiThread {
                                uiTest3()
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 模拟网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * 协程写法
     */
    fun coroutineTest() {
        viewModelScope.launch(Dispatchers.Main) {
            networkTests1()
            uiTest1()
            networkTests2()
            uiTest2()
            networkTests3()
            uiTest3()
        }
    }

    fun queryBanner() {
        viewModelScope.launch(Dispatchers.Main) {
            val banner = RetrofitHelper.wanAPI.getBanner()
            msg.value = banner.data[0].title
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

    private fun networkTest1() {
        printThreadInfo("networkTest1()")
    }

    private fun networkTest2() {
        printThreadInfo("networkTest2()")
    }

    private fun networkTest3() {
        printThreadInfo("networkTest3()")
    }

    private suspend fun networkTests1() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTest1()")
    }

    private suspend fun networkTests2() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTest2()")
    }

    private suspend fun networkTests3() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTest3()")
    }

    private fun printThreadInfo(prefix: String = "") {
        Log.i(
            "CoroutineDemo",
            "${if (prefix.isNullOrBlank()) "" else "$prefix, "}current thread is ${Thread.currentThread().name}"
        )
    }

}