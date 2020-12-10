package com.wd.kt.annotation

import android.app.Activity
import android.content.Context
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
            val temp = item.annotations
            if (!item.isAnnotationPresent(AutoWired::class.java)) continue
            var key = item.getAnnotation(AutoWired::class.java).value
            if(key.isNullOrEmpty()) key = item.name
        }
    }

}