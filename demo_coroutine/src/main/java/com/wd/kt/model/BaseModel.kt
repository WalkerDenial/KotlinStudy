package com.wd.kt.model

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/10 22:57.
 */
open class BaseModel<T> {
    var data: T? = null
    val errorCode: Int = 0
    val errorMsg: String = ""
}