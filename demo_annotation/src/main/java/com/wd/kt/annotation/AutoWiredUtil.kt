package com.wd.kt.annotation

import androidx.fragment.app.Fragment
import java.lang.reflect.Field

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/10/20 11:48 PM.
 */
object AutoWiredUtil {

    fun inject(fragment: Fragment) {
        val fields = fragment.javaClass.declaredFields
        if (fields.isNullOrEmpty()) return
        for (item in fields) {
            if (!item.isAnnotationPresent(AutoWired::class.java)) continue
            var key = item.getDeclaredAnnotation(AutoWired::class.java).value
            if (key.isNullOrEmpty()) key = item.name
            item.invokeValue(fragment, key)
        }
    }

    private fun <T : Fragment> Field.invokeValue(fragment: T, key: String) {
        val intent = fragment.requireActivity().intent ?: return
        val value = when (this.type) {
            Byte::class.java -> intent?.getByteExtra(key, Byte.MIN_VALUE)
            Short::class.java -> intent?.getShortExtra(key, Short.MIN_VALUE)
            Int::class.java -> intent?.getIntExtra(key, Int.MIN_VALUE)
            Long::class.java -> intent?.getLongExtra(key, Long.MIN_VALUE)
            Float::class.java -> intent?.getFloatExtra(key, Float.MIN_VALUE)
            Double::class.java -> intent?.getDoubleExtra(key, Double.MIN_VALUE)
            Char::class.java -> intent?.getCharExtra(key, Char.MIN_VALUE)
            String::class.java -> intent?.getStringExtra(key).orEmpty()
            Boolean::class.java -> intent?.getBooleanExtra(key, false)
            else -> return
        }
        this.isAccessible = true
        this.set(fragment, value)
    }

}