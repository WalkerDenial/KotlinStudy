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
     * 模拟网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * 协程写法
     */
    fun coroutineTest() {
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
     * 模拟网络请求以及更新 UI
     * 1. 请求接口 1 --> 更新 UI 1
     * 2. 请求接口 2 --> 更新 UI 2
     * 3. 请求接口 3 --> 更新 UI 3
     *
     * RxJava 写法
     */
    fun rxJavaTest() {
        networkTestRx1()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                uiTest1()
                networkTestRx2()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap {
                uiTest2()
                networkTestRx3()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { uiTest3() }
    }

    fun coroutineCompose() {
        viewModelScope.launch(Dispatchers.Main) {
            val banner = async { RetrofitHelper.wanAPI.queryBannerKt().data }
            val chapter = async { RetrofitHelper.wanAPI.queryChapterKt().data }
            msg.value = "${banner.await()?.first()?.title} + ${chapter.await()?.first()?.name}"
        }
    }

    fun rxJavaCompose() {
        Single.zip(
            RetrofitHelper.wanAPI.queryBannerRx(),
            RetrofitHelper.wanAPI.queryChapterRx(),
            { banner, chapter -> "${banner.data?.first()?.title} - ${chapter.data?.first()?.name}" }
        ).observeOn(AndroidSchedulers.mainThread()).subscribe { combine -> msg.value = combine }
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

    private suspend fun networkTestKt1() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTestKt1()")
    }

    private suspend fun networkTestKt2() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTestKt2()")
    }

    private suspend fun networkTestKt3() = withContext(Dispatchers.IO) {
        printThreadInfo("networkTestKt3()")
    }

    private fun networkTestRx1(): Observable<List<String>> {
        return Observable.create {
            printThreadInfo("networkTestRx1()")
            arrayListOf("networkTestRx1")
        }
    }

    private fun networkTestRx2(): Observable<String> {
        return Observable.create {
            printThreadInfo("networkTestRx2()")
            arrayListOf("networkTestRx2")
        }
    }

    private fun networkTestRx3(): Observable<String> {
        return Observable.create {
            printThreadInfo("networkTestRx3()")
            arrayListOf("networkTestRx3")
        }
    }

    private fun printThreadInfo(prefix: String = "") {
        Log.i(
            "CoroutineDemo",
            "${if (prefix.isNullOrBlank()) "" else "$prefix, "}current thread is ${Thread.currentThread().name}"
        )
    }

}