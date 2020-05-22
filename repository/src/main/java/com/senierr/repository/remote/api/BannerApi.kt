package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.Banner
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.UserInfo
import com.senierr.repository.entity.dto.Website
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * 用户模块API
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface BannerApi {

    /**
     * 获取首页Banner
     */
    @GET("banner/json")
    suspend fun getHomeBanner(): HttpResponse<MutableList<Banner>>
}