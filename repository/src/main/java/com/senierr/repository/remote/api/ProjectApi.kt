package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.entity.dto.Chapter
import retrofit2.http.*

/**
 * 项目模块API
 *
 * @author zhouchunjie
 * @date 2020/5/25
 */
interface ProjectApi {

    /**
     * 获取分类列表
     */
    @GET("project/tree/json")
    suspend fun getChapters(): HttpResponse<MutableList<Chapter>>

    /**
     * 获取某分类下项目列表数据
     *
     * @param cid 分类ID
     * @param pageIndex 页索引，从1开始
     */
    @GET("project/list/{pageIndex}/json")
    suspend fun getArticles(
        @Path("pageIndex") pageIndex: Int,
        @Query("cid") cid: Long
    ): HttpResponse<PageResult<Article>>
}