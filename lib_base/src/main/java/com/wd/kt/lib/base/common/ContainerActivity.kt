package com.wd.kt.lib.base.common

import android.os.Bundle
import com.wd.kt.lib.base.R
import com.wd.kt.lib.base.activity.BaseActivity
import com.wd.kt.lib.base.fragment.BaseFragment

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/7 23:36.
 */
open class ContainerActivity<F : BaseFragment>(private val fragment: F) :
    BaseActivity(R.layout.container_activity) {

    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commitNow()
        }
    }

}