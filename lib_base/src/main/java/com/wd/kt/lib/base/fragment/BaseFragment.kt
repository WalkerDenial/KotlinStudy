package com.wd.kt.lib.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/8 00:13.
 */
abstract class BaseFragment(@LayoutRes protected val layoutId: Int) : Fragment() {

    /**
     * 视图信息
     */
    private lateinit var contentView: View

    /**
     * 在此获取并设置视图信息
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        contentView = getContentView(inflater, container)
        return contentView
    }

    /**
     * 获取视图，该方法默认获取 layoutId 作为视图加载。
     * 若需要自定义视图内容，
     * 例如：
     *  1.添加 DataBinding；
     *  2、手动设置 view；
     * 时可以通过重写该方法即可实现
     */
    open fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        return inflater.inflate(layoutId, container, false)
    }

    /**
     * 当 View 创建后，初始化数据信息、视图信息等
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData(savedInstanceState)
        initView(savedInstanceState)
    }

    /**
     * 初始化数据信息，非必须
     */
    open fun initData(savedInstanceState: Bundle?) {

    }

    /**
     * 初始化视图信息，非必须
     */
    open fun initView(savedInstanceState: Bundle?) {

    }

}