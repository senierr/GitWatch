package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.Repo
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.api.RepositoryApi
import com.senierr.repository.remote.api.UserApi
import com.senierr.repository.service.api.IRepositoryService
import com.senierr.repository.sp.SPKey
import com.senierr.repository.sp.SPManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @author zhouchunjie
 * @date 2020/5/15
 */
class RepositoryService : IRepositoryService {

    private val repositoryApi by lazy { RemoteManager.getNormalHttp().create(RepositoryApi::class.java) }
    private val spUtil by lazy { SPManager.getSP() }

    override suspend fun searchRepositories(
        keywords: String,
        page: Int,
        perPage: Int,
        owner: String?,
        fork: Boolean,
        language: String?,
        sort: String?,
        order: String?
    ): MutableList<Repo> {
        return withContext(Dispatchers.IO) {
            val accessToken = spUtil.getString(SPKey.USER_LOGIN_TOKEN)
            return@withContext repositoryApi.searchRepositories(
                accessToken, keywords, page, perPage,
                owner, fork, language, sort, order)
        }
    }
}