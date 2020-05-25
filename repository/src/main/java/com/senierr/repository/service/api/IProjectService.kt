package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.entity.dto.Chapter

/**
 * 项目服务
 *
 * @author zhouchunjie
 * @date 2020/5/25
 */
interface IProjectService {

    /**
     * 获取分类列表
     */
    suspend fun getChapters(): MutableList<Chapter>

    /**
     * 获取某分类下项目列表数据
     *
     * @param cid 分类ID
     * @param pageIndex 页索引，从1开始
     */
    suspend fun getArticles(pageIndex: Int, cid: Long): PageResult<Article>
}