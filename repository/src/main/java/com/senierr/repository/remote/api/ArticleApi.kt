package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.PageResult
import retrofit2.http.*

/**
 * 用户模块API
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface ArticleApi {

    /**
     * 获取文章列表
     *
     * @param pageIndex 页索引，从0开始
     */
    @GET("article/list/{pageIndex}/json")
    suspend fun getArticles(@Path("pageIndex") pageIndex: Int): HttpResponse<PageResult<Article>>

    /**
     * 获取置顶文章
     */
    @GET("article/top/json")
    suspend fun getTopArticles(): HttpResponse<MutableList<Article>>

    /**
     * 获取体系下文章列表
     *
     * @param pageIndex 页索引，从0开始
     * @param cid 体系ID
     */
    @GET("article/list/{pageIndex}/json")
    suspend fun getArticlesByCourse(
        @Path("pageIndex") pageIndex: Int,
        @Query("cid") cid: Long
    ): HttpResponse<PageResult<Article>>

    /**
     * 获取作者下文章列表
     *
     * @param pageIndex 页索引，从0开始
     * @param author 作者
     */
    @GET("article/list/{pageIndex}/json")
    suspend fun getArticlesByAuthor(
        @Path("pageIndex") pageIndex: Int,
        @Query("author") author: String
    ): HttpResponse<PageResult<Article>>

    /**
     * 获取作者下文章列表
     *
     * @param pageIndex 页索引，从0开始
     * @param keyword 搜索词，多个关键词以空格间隔
     */
    @POST("article/query/{pageIndex}/json")
    suspend fun searchArticles(
        @Path("pageIndex") pageIndex: Int,
        @Field("k") keyword: String
    ): HttpResponse<PageResult<Article>>
}