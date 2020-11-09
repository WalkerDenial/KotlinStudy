package com.wd.kt.net

import com.wd.kt.model.BannerModel
import retrofit2.http.GET

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/9 23:18.
 */
interface WanAPI {

    @GET("banner/json")
    suspend fun getBanner(): BannerModel

}