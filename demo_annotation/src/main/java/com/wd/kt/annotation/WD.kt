package com.wd.kt.annotation

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 12/1/20 12:00 AM.
 * @Desc 自定义注解
 */
@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
annotation class WD()


/**
 * 测试自定义注解
 */
@WD
class AnnotationTestClass