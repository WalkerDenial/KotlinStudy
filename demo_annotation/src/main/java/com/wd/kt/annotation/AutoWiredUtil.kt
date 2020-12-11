package com.wd.kt.annotation

import androidx.fragment.app.Fragment

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/10/20 11:48 PM.
 */
object AutoWiredUtil {

    fun inject(context: Fragment) {
        val fields = context.javaClass.declaredFields
        if (fields.isNullOrEmpty()) return
        for (item in fields) {
            if (!item.isAnnotationPresent(AutoWired::class.java)) continue
            var key = item.getDeclaredAnnotation(AutoWired::class.java).value
            if (key.isNullOrEmpty()) key = item.name
            val intent = context.activity?.intent
            when (item.type) {
                String::class.java -> {
                    val value = intent?.getStringExtra(key).orEmpty()
                    item.isAccessible = true
                    item.set(context, value)
                }
                Int::class.java -> {
                    val value = intent?.getIntExtra(key, 0)
                    item.isAccessible = true
                    item.set(context, value)
                }
            }
        }
    }

}