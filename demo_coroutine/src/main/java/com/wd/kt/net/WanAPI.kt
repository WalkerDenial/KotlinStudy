package com.wd.kt.net

import com.wd.kt.model.BannerItemModel
import com.wd.kt.model.BaseModel
import com.wd.kt.model.ChapterItemModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

/**
 *
 * @Author Walker Denial
 * @Email WalkerDenial@gmail.com
 * @Time Created at 2020/11/9 23:18.
 */
interface WanAPI {

    @GET("banner/json")
    suspend fun queryBannerKt(): BaseModel<List<BannerItemModel>>

    @GET("wxarticle/chapters/json")
    suspend fun queryChapterKt(): BaseModel<List<ChapterItemModel>>

    @GET("banner/json")
    fun queryBannerRx(): Single<BaseModel<List<BannerItemModel>>>

    @GET("wxarticle/chapters/json")
    fun queryChapterRx(): Single<BaseModel<List<ChapterItemModel>>>

}