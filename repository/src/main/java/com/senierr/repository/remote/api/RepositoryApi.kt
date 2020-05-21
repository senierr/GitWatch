package com.senierr.repository.remote.api

import com.senierr.repository.entity.dto.Repo
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 仓库服务
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface RepositoryApi {

    /**
     * 搜索仓库
     *
     * @param accessToken 用户授权码
     * @param keywords 搜索关键字
     * @param page 当前的页码
     * @param perPage 每页的数量，最大为100
     * @param owner 筛选指定空间地址(企业、组织或个人的地址 path)的仓库
     * @param fork 是否搜索含 fork 的仓库，默认：否
     * @param language 筛选指定语言的仓库
     * @param sort 排序字段，last_push_at(更新时间)、stars_count(收藏数)、forks_count(Fork 数)、watches_count(关注数)，默认为最佳匹配
     * @param order 排序顺序: desc(default)、asc
     */
    @GET("api/v5/search/repositories")
    suspend fun searchRepositories(
        @Query("access_token") accessToken: String,
        @Query("q") keywords: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 20,
        @Query("owner") owner: String? = null,
        @Query("fork") fork: Boolean = false,
        @Query("language") language: String? = null,
        @Query("sort") sort: String? = null,
        @Query("order") order: String? = "desc"
    ): MutableList<Repo>

}