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
abstract class ContainerActivity<F : BaseFragment> : BaseActivity(R.layout.container_activity) {

    abstract fun getContentFragment(): F

    /**
     * 将获取到的 fragment 添加到界面上
     */
    override fun initView(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, getContentFragment())
                .commitNow()
        }
    }

}