package com.wd.kt.coroutine.main

import android.os.Bundle
import com.wd.kt.coroutine.R
import com.wd.kt.coroutine.databinding.MainFragmentBinding
import com.wd.kt.lib.base.fragment.BaseVMFragment

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
        viewModel.globalCoroutineTest()

        // 模拟网络请求以及更新 UI
        // 1. 请求接口 1 --> 更新 UI 1
        // 2. 请求接口 2 --> 更新 UI 2
        // 3. 请求接口 3 --> 更新 UI 3

        // 线程写法
        viewModel.threadTest(activity)

        // 协程写法
        viewModel.coroutineTest()
    }

}