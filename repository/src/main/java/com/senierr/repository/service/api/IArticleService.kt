package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.PageResult

/**
 * 文章服务
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface IArticleService {

    /**
     * 获取文章列表
     *
     * @param pageIndex 页索引，从0开始
     */
    suspend fun getArticles(pageIndex: Int): PageResult<Article>

    /**
     * 获取置顶文章
     */
    suspend fun getTopArticles(): MutableList<Article>

    /**
     * 获取体系下文章列表
     *
     * @param pageIndex 页索引，从0开始
     * @param cid 体系ID
     */
    suspend fun getArticlesByCourse(pageIndex: Int, cid: Long): PageResult<Article>

    /**
     * 获取作者下文章列表
     *
     * @param pageIndex 页索引，从0开始
     * @param author 作者
     */
    suspend fun getArticlesByAuthor(pageIndex: Int, author: String): PageResult<Article>

    /**
     * 获取作者下文章列表
     *
     * @param pageIndex 页索引，从0开始
     * @param keyword 搜索词，多个关键词以空格间隔
     */
    suspend fun searchArticles(pageIndex: Int, keyword: String): PageResult<Article>
}