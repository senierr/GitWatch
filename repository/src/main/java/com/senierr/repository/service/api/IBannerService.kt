package com.senierr.repository.service.api

import com.senierr.repository.entity.dto.Banner

/**
 * 轮播图服务
 *
 * @author zhouchunjie
 * @date 2020/5/7
 */
interface IBannerService {

    /**
     * 获取首页Banner
     */
    suspend fun getHomeBanner(): MutableList<Banner>
}