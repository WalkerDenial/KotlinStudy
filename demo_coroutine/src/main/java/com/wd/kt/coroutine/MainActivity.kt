package com.wd.kt.coroutine

import com.wd.kt.coroutine.main.MainFragment
import com.wd.kt.lib.base.activity.ContainerActivity

class MainActivity : ContainerActivity<MainFragment>() {

    override fun getContentFragment(): MainFragment = MainFragment()

}