package com.wd.kt.annotation

import androidx.annotation.IntDef

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/2/20 11:41 PM.
 */

/**
 * 列举周几信息
 * 常规写法：枚举
 */
enum class Weeks {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY
}

const val MONDAY = 0
const val TUESDAY = 1
const val WEDNESDAY = 2
const val THURSDAY = 3
const val FRIDAY = 4
const val SATURDAY = 5
const val SUNDAY = 6

@IntDef(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY)
@Target(AnnotationTarget.FIELD, AnnotationTarget.TYPE_PARAMETER, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
annotation class WeekDays

fun showWeek(@WeekDays day: Int) {
    print(day)
}

fun test() {
    showWeek(99) // Java 版本此处编译器会直接报错，Kotlin 目前还存在问题
    showWeek(THURSDAY) // 只有限定范围内的数据，传入才能通过编译
}

