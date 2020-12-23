package com.wd.kt.annotation

import com.wd.kt.annotation.main.SecondFragment
import com.wd.kt.lib.base.activity.ContainerActivity

class SecondActivity : ContainerActivity<SecondFragment>() {

    override fun getContentFragment(): SecondFragment = SecondFragment()

}