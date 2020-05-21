package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.Repo

/**
 * 仓库服务
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface IRepositoryService {

    /**
     * 搜索仓库
     *
     * @param keywords 搜索关键字
     * @param page 当前的页码
     * @param perPage 每页的数量，最大为100
     * @param owner 筛选指定空间地址(企业、组织或个人的地址 path)的仓库
     * @param fork 是否搜索含 fork 的仓库，默认：否
     * @param language 筛选指定语言的仓库
     * @param sort 排序字段，last_push_at(更新时间)、stars_count(收藏数)、forks_count(Fork 数)、watches_count(关注数)，默认为最佳匹配
     * @param order 排序顺序: desc(default)、asc
     */
    suspend fun searchRepositories(
        keywords: String,
        page: Int,
        perPage: Int = 20,
        owner: String? = null,
        fork: Boolean = false,
        language: String? = null,
        sort: String? = null,
        order: String? = "desc"
    ): MutableList<Repo>
}