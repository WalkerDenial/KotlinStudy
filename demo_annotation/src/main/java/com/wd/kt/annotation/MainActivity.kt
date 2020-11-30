package com.wd.kt.annotation

import com.wd.kt.annotation.main.MainFragment
import com.wd.kt.lib.base.common.ContainerActivity

class MainActivity : ContainerActivity<MainFragment>() {

    override fun getContentFragment(): MainFragment = MainFragment()

}