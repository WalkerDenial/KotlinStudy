package com.wd.kt.annotation.main

import com.wd.kt.annotation.R
import com.wd.kt.annotation.databinding.MainFragmentBinding
import com.wd.kt.lib.base.fragment.BaseVMFragment

class MainFragment : BaseVMFragment<MainFragmentBinding, MainViewModel>(
    R.layout.main_fragment,
    MainViewModel::class.java
) {

    override fun onBindingConfig(viewModel: MainViewModel, binding: MainFragmentBinding) {
        binding.vm = viewModel
    }

}