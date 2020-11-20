package com.wd.kt.generic.main

import com.wd.kt.generic.main.MainFragment
import com.wd.kt.lib.base.common.ContainerActivity

class MainActivity : ContainerActivity<MainFragment>() {

    override fun getContentFragment(): MainFragment = MainFragment()

}