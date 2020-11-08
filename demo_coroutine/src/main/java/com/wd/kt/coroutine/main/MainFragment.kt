package com.wd.kt.coroutine.main

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

}