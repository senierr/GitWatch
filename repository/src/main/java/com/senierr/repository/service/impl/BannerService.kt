package com.senierr.repository.service.impl

import com.senierr.repository.entity.dto.Banner
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.api.BannerApi
import com.senierr.repository.service.api.IBannerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *
 * @author zhouchunjie
 * @date 2020/5/22
 */
class BannerService : IBannerService {

    private val bannerApi by lazy { RemoteManager.getNormalHttp().create(BannerApi::class.java) }

    override suspend fun getHomeBanner(): MutableList<Banner> {
        return withContext(Dispatchers.IO) {
            val response = bannerApi.getHomeBanner()
            if (!response.isSuccessful()) throw response.getException()
            return@withContext response.data
        }
    }
}