package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.Website
import retrofit2.http.GET

/**
 * 工具模块API
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface UtilApi {

    /**
     * 获取常用网址
     */
    @GET("friend/json")
    suspend fun getUsefulWebsites(): HttpResponse<MutableList<Website>>

    /**
     * 获取搜索热词
     */
    @GET("friend/json")
    suspend fun getHotSearchKeywords(): HttpResponse<MutableList<Website>>
}