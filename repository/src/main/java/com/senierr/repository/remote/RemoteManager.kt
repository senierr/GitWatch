package com.senierr.repository.remote

import android.content.Context
import com.itkacher.okhttpprofiler.OkHttpProfilerInterceptor
import com.senierr.repository.remote.cookie.CookieJarImpl
import com.senierr.repository.remote.cookie.store.SPCookieStore
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

    fun initialize(context: Context, isDebug: Boolean) {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (isDebug) {
            okHttpClientBuilder.addInterceptor(OkHttpProfilerInterceptor())
        }
        okHttpClientBuilder.connectTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.readTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.writeTimeout(Constant.TIMEOUT, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.cookieJar(CookieJarImpl(SPCookieStore(context)))
        retrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClientBuilder.build())
            .build()
    }

    fun getNormalHttp(): Retrofit {
        return retrofit
    }
}