package com.wd.kt.generic.main

import com.wd.kt.lib.base.activity.ContainerActivity

class MainActivity : ContainerActivity<MainFragment>() {

    override fun getContentFragment(): MainFragment = MainFragment()

}