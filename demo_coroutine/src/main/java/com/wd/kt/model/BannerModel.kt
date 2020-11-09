package com.wd.kt.model

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/9 23:22.
 */
data class BannerModel(
    val data: List<Data>,
    val errorCode: Int,
    val errorMsg: String
)

data class Data(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)