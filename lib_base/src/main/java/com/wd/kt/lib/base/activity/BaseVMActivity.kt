package com.wd.kt.lib.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.wd.kt.lib.base.vm.BaseViewModel

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/7 23:25.
 */
abstract class BaseVMActivity<DB : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes layoutId: Int,
    private val clazz: Class<VM>
) : BaseActivity(layoutId) {

    private lateinit var binding: DB
    private lateinit var viewModel: VM

    abstract fun onBindingConfig(viewModel: VM, binding: DB)

    override fun initContentView() {
        binding = DataBindingUtil.setContentView(this, layoutId)
        viewModel = ViewModelProvider(this).get(clazz)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
        onBindingConfig(viewModel, binding)
    }

}