package com.wd.kt.annotation

import androidx.fragment.app.Fragment

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/10/20 11:48 PM.
 */
object AutoWiredUtil {

    /**
     * 通过反射获取到 Fragment 中的所有属性
     */
    fun inject(fragment: Fragment) {
        val fields = fragment.javaClass.declaredFields
        if (fields.isNullOrEmpty()) return
        val extra = fragment.activity?.intent?.extras ?: return
        for (item in fields) {
            if (!item.isAnnotationPresent(AutoWired::class.java)) continue
            var key = item.getDeclaredAnnotation(AutoWired::class.java).value
            if (key.isNullOrEmpty()) key = item.name
            item.isAccessible = true
            item.set(fragment, extra.get(key))
        }
    }

}