package com.wd.kt.generic

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 11/28/20 12:23 AM.
 */
class GenericRestrict<T> {

    /**
     * 错误
     * 原因：泛型中不能实例化类型变量
     */
    // private var t = T()

    companion object {
        /**
         * 错误
         * 原因：静态域中不能引用类型变量
         */
        // private var t = T()

        /**
         * 错误
         * 原因：静态方法中不能引用类型变量
         */
        // fun test(t: T) {

        // }

        /**
         * 静态方法如果是泛型方法，那么可以正常使用
         */
        fun <T> test(t: T) {

        }
    }

}