package com.wd.kt.net

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/9 23:15.
 */
object RetrofitHelper {

    /**
     * 从 wanandroid 公开 API 获取信息
     */
    private const val HOST = "https://www.wanandroid.com/"

    /**
     * 懒加载的方式获取 retrofit 实例
     */
    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val wanAPI: WanAPI = retrofit.create(WanAPI::class.java)

}