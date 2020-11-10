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

    private const val HOST = "https://www.wanandroid.com/"

    private val retrofit by lazy {
        Retrofit.Builder().baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    val wanAPI = retrofit.create(WanAPI::class.java)

}