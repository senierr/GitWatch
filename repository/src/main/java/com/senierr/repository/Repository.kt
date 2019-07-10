package com.senierr.repository

import android.arch.persistence.room.Room
import android.content.Context
import com.senierr.base.support.utils.DiskLruCacheUtil
import com.senierr.base.support.utils.SPUtil
import com.senierr.http.RxHttp
import com.senierr.http.interceptor.LogInterceptor
import com.senierr.repository.db.AppDatabase
import com.senierr.repository.remote.RemoteApi
import com.senierr.repository.service.api.IUserService
import com.senierr.repository.service.impl.UserService

/**
 * 数据仓库
 *
 * @author zhouchunjie
 * @date 2019/7/5 20:17
 */
object Repository {

    private const val DEBUG_TAG = "Repository"
    private const val TIMEOUT = 15 * 1000L

    private const val DB_NAME = "app_repository.db"
    private const val SP_NAME = "app_repository"

    internal lateinit var rxHttp: RxHttp
    internal lateinit var database: AppDatabase
    internal lateinit var spUtil: SPUtil
    internal var diskLruCacheUtil: DiskLruCacheUtil? = null

    fun initialize(context: Context, isDebug: Boolean) {
        rxHttp = RxHttp.Builder()
            .apply {
                if (isDebug) {
                    debug(DEBUG_TAG, LogInterceptor.LogLevel.BODY)
                }
            }
            .connectTimeout(TIMEOUT)
            .readTimeout(TIMEOUT)
            .writeTimeout(TIMEOUT)
            .baseUrl(RemoteApi.BASE_URL)
            .build()
        // 数据库
        database = Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
        // SharedPreferences
        spUtil = SPUtil.getInstance(context, SP_NAME)
        // Disk
//        diskLruCacheUtil = DiskLruCacheUtil.getInstance(context.externalCacheDir)
    }

    /**
     * 获取数据服务
     */
    inline fun <reified T> getService(): T = when(T::class.java) {
        IUserService::class.java ->
            UserService() as T
        else -> throw IllegalArgumentException("Can not find this type of the service!")
    }
}