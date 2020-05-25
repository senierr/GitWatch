package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.entity.dto.Chapter

/**
 * 公众号服务
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface IWxArticleService {

    /**
     * 获取公众号列表
     */
    suspend fun getChapters(): MutableList<Chapter>

    /**
     * 获取某公众号历史数据
     *
     * @param id 公众号ID
     * @param pageIndex 页索引，从0开始
     */
    suspend fun getArticles(id: Long, pageIndex: Int): PageResult<Article>

    /**
     * 在某个公众号中搜索历史文章
     *
     * @param id 公众号ID
     * @param pageIndex 页索引，从0开始
     * @param keyword 关键词
     */
    suspend fun searchByChapterId(id: Long, pageIndex: Int, keyword: String): PageResult<Article>
}