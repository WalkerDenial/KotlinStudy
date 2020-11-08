package com.wd.kt.coroutine.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.wd.kt.coroutine.R
import com.wd.kt.coroutine.databinding.MainFragmentBinding
import com.wd.kt.lib.base.fragment.BaseVMFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

class MainFragment : BaseVMFragment<MainFragmentBinding, MainViewModel>(
    R.layout.main_fragment,
    MainViewModel::class.java
) {

    override fun onBindingConfig(viewModel: MainViewModel, binding: MainFragmentBinding) {
        binding.vm = viewModel
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        coroutineTest()
    }

    private fun coroutineTest() {
        // 使用 GlobalScope 作用域，通过 launch 开启一段协程
        GlobalScope.launch(Dispatchers.IO) {
            printThreadInfo()
        }

        // 模拟网络请求以及更新 UI
        // 1. 请求接口 1 --> 更新 UI 1
        // 2. 请求接口 2 --> 更新 UI 2
        // 3. 请求接口 3 --> 更新 UI 3

        // 线程写法
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

        // 协程写法
        viewModel.viewModelScope.launch(Dispatchers.Main) {
            networkTests1()
            uiTest1()
            networkTests2()
            uiTest2()
            networkTests3()
            uiTest3()
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