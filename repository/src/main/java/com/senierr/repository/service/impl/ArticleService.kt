package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.HttpResponse
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.api.ArticleApi
import com.senierr.repository.service.api.IArticleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @author zhouchunjie
 * @date 2020/5/15
 */
class ArticleService : IArticleService {

    private val articleApi by lazy { RemoteManager.getNormalHttp().create(ArticleApi::class.java) }

    override suspend fun getArticles(pageIndex: Int): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = articleApi.getArticles(pageIndex)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun getTopArticles(): MutableList<Article> {
        return withContext(Dispatchers.IO) {
            val response = articleApi.getTopArticles()
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun getArticlesByCourse(pageIndex: Int, cid: Long): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = articleApi.getArticlesByCourse(pageIndex, cid)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun getArticlesByAuthor(pageIndex: Int, author: String): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = articleApi.getArticlesByAuthor(pageIndex, author)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun searchArticles(pageIndex: Int, keyword: String): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = articleApi.searchArticles(pageIndex, keyword)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }
}