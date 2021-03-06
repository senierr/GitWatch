package com.senierr.repository.remote

import android.content.Context
import com.senierr.repository.remote.cookie.CookieJarImpl
import com.senierr.repository.remote.cookie.store.CookieStore
import com.senierr.repository.remote.cookie.store.SPCookieStore
import com.senierr.repository.remote.interceptor.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * 网络数据管理器
 *
 * @author zhouchunjie
 * @date 2019/11/27
 */
object RemoteManager {

    private lateinit var retrofit: Retrofit
    private lateinit var cookieStore: CookieStore

    fun initialize(context: Context, isDebug: Boolean) {
        cookieStore = SPCookieStore(context)
        val okHttpClientBuilder = OkHttpClient.Builder().apply {
            if (isDebug) { addInterceptor(LoggingInterceptor()) }
            connectTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
            readTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
            writeTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
            cookieJar(CookieJarImpl(cookieStore))
        }
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    fun getNormalHttp(): Retrofit = retrofit

    fun getCookieStore(): CookieStore = cookieStore
}