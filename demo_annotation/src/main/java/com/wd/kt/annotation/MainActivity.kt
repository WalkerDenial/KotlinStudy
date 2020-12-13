package com.wd.kt.annotation

import android.content.Intent
import android.view.View
import com.wd.kt.annotation.main.MainFragment
import com.wd.kt.lib.base.common.ContainerActivity

class MainActivity : ContainerActivity<MainFragment>() {

    override fun getContentFragment(): MainFragment = MainFragment()

    fun onClick(view: View) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("name", "Wang")
        intent.putExtra("nick_name", "Walker")
        intent.putExtra("age", 99)
        startActivity(intent)
    }

}