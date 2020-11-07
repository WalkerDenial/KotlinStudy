package com.wd.kt.lib.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/7 23:25.
 */
abstract class BaseActivity(@LayoutRes val layoutId: Int) : AppCompatActivity() {

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initData(savedInstanceState)
        initView(savedInstanceState)
    }

    open fun initContentView() {
        setContentView(layoutId)
    }

    open fun initData(savedInstanceState: Bundle?) {

    }

}