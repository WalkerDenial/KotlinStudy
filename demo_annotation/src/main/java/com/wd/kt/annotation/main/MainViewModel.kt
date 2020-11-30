package com.wd.kt.coroutine.main

import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.wd.kt.lib.base.vm.BaseViewModel
import com.wd.kt.net.RetrofitHelper
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class MainViewModel : BaseViewModel() {

    /**
     * 界面展示数据
     */
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
     * 模拟串行网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * 线程写法
     */
    fun <A : ComponentActivity> threadSerialTest(activity: A?) {
        thread {
            networkTest1()
            activity?.runOnUiThread {
                uiTest1()
                thread {
                    networkTest2()
                    activity.runOnUiThread {
                        uiTest2()
                        thread {
                            networkTest3()
                            activity.runOnUiThread {
                                uiTest3()
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 模拟串行网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * 协程写法
     */
    fun coroutineSerialTest() {
        viewModelScope.launch(Dispatchers.Main) {
            networkTestKt1()
            uiTest1()
            networkTestKt2()
            uiTest2()
            networkTestKt3()
            uiTest3()
        }
    }

    /**
     * 模拟串行网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * RxJava 写法
     */
    fun rxJavaSerialTest() {
        Observable.just("Test")
            .observeOn(Schedulers.io())
            .doOnNext { networkTest1() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { uiTest1() }
            .observeOn(Schedulers.io())
            .doOnNext { networkTest2() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { uiTest2() }
            .observeOn(Schedulers.io())
            .doOnNext { networkTest3() }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { uiTest3() }
            .subscribe()
    }

    /**
     * 线程并行网络请求
     */
    fun <A : ComponentActivity> threadParallelTest(activity: A?) {
        thread {
            networkTest1()
            activity?.runOnUiThread { uiTest1() }
        }
        thread {
            networkTest2()
            activity?.runOnUiThread { uiTest2() }
        }
        thread {
            networkTest3()
            activity?.runOnUiThread { uiTest3() }
        }
    }

    /**
     * 协程并行网络请求
     */
    fun coroutineParallelTest() {
        viewModelScope.launch(Dispatchers.Main) {
            val banner = async { RetrofitHelper.wanAPI.queryBannerKt().data }
            val chapter = async { RetrofitHelper.wanAPI.queryChapterKt().data }
            msg.value = "${banner.await()?.first()?.title} + ${chapter.await()?.first()?.name}"
        }
    }

    /**
     * RxJava 网络并行请求
     */
    fun rxJavaParallelTest() {
        Single.zip(
            RetrofitHelper.wanAPI.queryBannerRx(),
            RetrofitHelper.wanAPI.queryChapterRx(),
            { banner, chapter -> "${banner.data?.first()?.title} - ${chapter.data?.first()?.name}" }
        ).observeOn(AndroidSchedulers.mainThread()).subscribe { combine -> msg.value = combine }
    }

    /**
     * 模拟 UI 线程操作，需要在主线程处理
     */
    private fun uiTest1() {
        printThreadInfo("uiTest1()")
    }

    /**
     * 模拟 UI 线程操作，需要在主线程处理
     */
    private fun uiTest2() {
        printThreadInfo("uiTest2()")
    }

    /**
     * 模拟 UI 线程操作，需要在主线程处理
     */
    private fun uiTest3() {
        printThreadInfo("uiTest3()")
    }

    /**
     * 模拟网络请求，需要异步
     */
    private fun networkTest1() {
        printThreadInfo("networkTest1()")
    }

    /**
     * 模拟网络请求，需要异步
     */
    private fun networkTest2() {
        printThreadInfo("networkTest2()")
    }

    /**
     * 模拟网络请求，需要异步
     */
    private fun networkTest3() {
        printThreadInfo("networkTest3()")
    }

    /**
     * 模拟网络请求，需要异步，kotlin coroutine 写法
     */
    private suspend fun networkTestKt1() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTestKt1()")
    }

    /**
     * 模拟网络请求，需要异步，kotlin coroutine 写法
     */
    private suspend fun networkTestKt2() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTestKt2()")
    }

    /**
     * 模拟网络请求，需要异步，kotlin coroutine 写法
     */
    private suspend fun networkTestKt3() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTestKt3()")
    }

    /**
     * 输出线程信息
     */
    private fun printThreadInfo(prefix: String = "") {
        Log.i(
            "CoroutineDemo",
            "${if (prefix.isNullOrBlank()) "" else "$prefix, "}current thread is ${Thread.currentThread().name}"
        )
    }

}