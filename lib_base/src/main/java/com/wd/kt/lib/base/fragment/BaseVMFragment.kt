package com.wd.kt.lib.base.fragment

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

    private lateinit var binding: DB
    private lateinit var viewModel: VM

    abstract fun onBindingConfig(viewModel: VM, binding: DB)

    override fun getContentView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        viewModel = ViewModelProvider(this).get(clazz)
        binding.lifecycleOwner = this
        onBindingConfig(viewModel, binding)
        return binding.root
    }

}