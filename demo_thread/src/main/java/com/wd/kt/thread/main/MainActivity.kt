package com.wd.kt.thread.main

import android.os.Bundle
import com.wd.kt.lib.base.activity.BaseActivity
import com.wd.kt.thread.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.concurrent.thread

/**
 * 上下文切换耗时测试
 * 本测试代码为一个干净的代码，只测试切换时间，不涉及对象临时存储等操作，如果加上业务，耗时只会增加
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/31/20 10:43 PM.
 */
class MainActivity : BaseActivity(R.layout.activity_main) {

    private var startTime = 0L
    private var mainToChild = 0L // 主线程切换到子线程耗时
    private var childToMain = 0L // 子线程切换到主线程耗时

    private val sb = StringBuilder()
    private val scope = MainScope()

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        tvMessage.text = ""
        sb.clear()
        testByThread()
    }

    /**
     * 传统的线程的方式测试上下文切换时间差
     */
    private fun testByThread() {
        sb.append("线程切换方式如下：\n")
        startTime = System.currentTimeMillis()
        thread {
            mainToChild = System.currentTimeMillis() - startTime
            sb.append("主线程 --> 子线程：$mainToChild ms\n")
            Thread.sleep(1000)
            startTime = System.currentTimeMillis()
            this@MainActivity.runOnUiThread {
                childToMain = System.currentTimeMillis() - startTime
                sb.append("子线程 --> 主线程：$childToMain ms\n\n")
                testByCoroutine()
            }
        }
    }

    /**
     * 协程方式测试上下文切换时间差
     */
    private fun testByCoroutine() {
        sb.append("协程切换方式如下：\n")
        startTime = System.currentTimeMillis()
        scope.launch(Dispatchers.Main) {
            toIOThread()
            childToMain = System.currentTimeMillis() - startTime
            sb.append("子线程 --> 主线程：$childToMain ms")
            tvMessage.text = sb
        }
    }

    private suspend fun toIOThread() {
        withContext(Dispatchers.IO) {
            mainToChild = System.currentTimeMillis() - startTime
            sb.append("主线程 --> 子线程：$mainToChild ms\n")
            delay(1000)
            startTime = System.currentTimeMillis()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.cancel()
    }

}