package com.wd.kt.generic

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/19 23:49.
 */
interface GenericInterface<T>

/**
 * 接口继承方式一
 */
interface GenericChildInterface1<T> : GenericInterface<T>

/**
 * 接口继承方式二
 */
interface GenericChildInterface2 : GenericInterface<String>