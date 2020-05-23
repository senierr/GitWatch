package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.entity.dto.WxChapter
import retrofit2.http.*

/**
 * 公众号模块API
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface WxArticleApi {

    /**
     * 获取公众号列表
     */
    @GET("wxarticle/chapters/json")
    suspend fun getChapters(): HttpResponse<MutableList<WxChapter>>

    /**
     * 获取某公众号历史数据
     *
     * @param id 公众号ID
     * @param pageIndex 页索引，从0开始
     */
    @GET("wxarticle/list/{id}/{pageIndex}/json")
    suspend fun getArticles(
        @Path("id") id: Long,
        @Path("pageIndex") pageIndex: Int
    ): HttpResponse<PageResult<Article>>

    /**
     * 在某个公众号中搜索历史文章
     *
     * @param id 公众号ID
     * @param pageIndex 页索引，从0开始
     * @param keyword 关键词
     */
    @GET("wxarticle/list/{id}/{pageIndex}/json")
    suspend fun searchByChapterId(
        @Path("id") id: Long,
        @Path("pageIndex") pageIndex: Int,
        @Query("k") keyword: String
    ): HttpResponse<PageResult<Article>>
}