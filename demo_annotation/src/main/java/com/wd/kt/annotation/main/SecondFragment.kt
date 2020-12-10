package com.wd.kt.annotation.main

import com.wd.kt.annotation.AutoWired
import com.wd.kt.annotation.AutoWiredUtil
import com.wd.kt.annotation.R
import com.wd.kt.annotation.databinding.SecondFragmentBinding
import com.wd.kt.lib.base.fragment.BaseVMFragment

class SecondFragment : BaseVMFragment<SecondFragmentBinding, SecondViewModel>(
    R.layout.second_fragment,
    SecondViewModel::class.java
) {
    @AutoWired
    var name: String = ""

    @AutoWired
    var age: Int = 0

    override fun onBindingConfig(viewModel: SecondViewModel, binding: SecondFragmentBinding) {
        binding.vm = viewModel
        activity?.let { AutoWiredUtil.inject(it) }
    }

}