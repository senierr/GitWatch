package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.entity.dto.WxChapter
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.api.WxArticleApi
import com.senierr.repository.service.api.IWxArticleService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @author zhouchunjie
 * @date 2020/5/23 13:26
 */
class WxArticleService : IWxArticleService {

    private val wxArticleApi by lazy {
        RemoteManager.getNormalHttp().create(WxArticleApi::class.java)
    }

    override suspend fun getChapters(): MutableList<WxChapter> {
        return withContext(Dispatchers.IO) {
            val response = wxArticleApi.getChapters()
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun getArticles(id: Long, pageIndex: Int): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = wxArticleApi.getArticles(id, pageIndex)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun searchByChapterId(
        id: Long,
        pageIndex: Int,
        keyword: String
    ): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = wxArticleApi.searchByChapterId(id, pageIndex, keyword)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }
}