package com.wd.kt.generic

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/19 23:50.
 */
open class GenericClass<T>

/**
 * 泛型继承方式一
 */
class GenericChildClass1<T> : GenericClass<T>()

/**
 * 泛型继承方式二
 */
class GenericChildClass2 : GenericClass<String>()