package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.Article
import com.senierr.repository.entity.dto.Chapter
import com.senierr.repository.entity.dto.PageResult
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.api.ProjectApi
import com.senierr.repository.service.api.IProjectService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @author zhouchunjie
 * @date 2020/5/25
 */
class ProjectService : IProjectService {

    private val projectApi by lazy {
        RemoteManager.getNormalHttp().create(ProjectApi::class.java)
    }

    override suspend fun getChapters(): MutableList<Chapter> {
        return withContext(Dispatchers.IO) {
            val response = projectApi.getChapters()
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }

    override suspend fun getArticles(pageIndex: Int, cid: Long): PageResult<Article> {
        return withContext(Dispatchers.IO) {
            val response = projectApi.getArticles(pageIndex, cid)
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }
}