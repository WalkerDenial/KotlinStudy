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

    /**
     * 在 Activity 创建的时候，做一些初始化操作
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        initData(savedInstanceState)
        initView(savedInstanceState)
    }

    /**
     * 设置 contentView
     * 可支持：
     * 1、layout resource
     * 2、自定义 view
     * 3、DataBinding 视图
     */
    open fun initContentView() {
        setContentView(layoutId)
    }

    /**
     * 初始化数据信息，为后续界面展示做铺垫，非必须
     */
    open fun initData(savedInstanceState: Bundle?) {

    }

    /**
     * 初始化视图信息，非必须
     */
    open fun initView(savedInstanceState: Bundle?) {

    }

}