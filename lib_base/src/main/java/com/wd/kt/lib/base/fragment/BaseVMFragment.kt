package com.wd.kt.lib.base.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.wd.kt.lib.base.vm.BaseViewModel

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/8 00:13.
 */
abstract class BaseVMFragment<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes layoutId: Int,
    private val clazz: Class<VM>
) : BaseFragment(layoutId) {

    /**
     * DataBinding 信息，严格来讲，除了绑定，子类不能对此对象进行操作
     */
    private lateinit var binding: DB
    protected lateinit var viewModel: VM

    abstract fun onBindingConfig(viewModel: VM, binding: DB)

    /**
     * 获取 ViewDataBinding 对象以及 viewModel
     */
    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = ViewModelProvider(this).get(clazz)
        binding.lifecycleOwner = this
        return binding.root
    }

    /**
     * 初始化数据时，将 viewModel 与 binding 绑定处理；
     */
    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        onBindingConfig(viewModel, binding)
    }

}