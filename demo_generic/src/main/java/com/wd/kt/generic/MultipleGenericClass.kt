package com.wd.kt.generic

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/19 23:52.
 */

open class A
open class B

interface C
interface D

/**
 * 错误写法
 * 原因：注意单继承，这里 A、B 都是 class，同时继承多个 class，违背单继承原则，无法编译通过
 */
//class MultipleGenericClass1<T : A, B, C, D>

/**
 * 错误写法
 * 原因：注意继承规则，如果存在 class，那么 class 一定要排在前面
 */
//class MultipleGenericClass2<T : C, A, D>

/**
 * 正确写法
 */
class MultipleGenericClass3<T : A, C, D>