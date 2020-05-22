package com.senierr.repository

import android.content.Context
import com.senierr.repository.db.DatabaseManager
import com.senierr.repository.disk.DiskManager
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.service.api.IArticleService
import com.senierr.repository.service.api.IBannerService
import com.senierr.repository.service.api.IUserService
import com.senierr.repository.service.impl.ArticleService
import com.senierr.repository.service.impl.BannerService
import com.senierr.repository.service.impl.UserService
import com.senierr.repository.sp.SPManager

/**
 * 数据仓库
 *
 * @author zhouchunjie
 * @date 2019/7/5 20:17
 */
object Repository {

    /**
     * 初始化
     */
    fun initialize(context: Context, isDebug: Boolean) {
        RemoteManager.initialize(context, isDebug)
        DatabaseManager.initialize(context)
        SPManager.initialize(context)
        DiskManager.initialize(context)
    }

    /**
     * 获取数据服务
     */
    inline fun <reified T> getService(): T = when (T::class.java) {
        IUserService::class.java -> UserService() as T
        IArticleService::class.java -> ArticleService() as T
        IBannerService::class.java -> BannerService() as T
        else -> throw IllegalArgumentException("Can not find this type of the service!")
    }
}