package com.senierr.repository.remote

import android.util.Base64
import com.senierr.repository.entity.UserInfo
import com.senierr.repository.service.GithubService
import com.senierr.http.RxHttp
import com.senierr.http.builder.RequestBuilder
import com.senierr.http.interceptor.LogInterceptor
import io.reactivex.Observable

/**
 * Github数据接口实现
 *
 * @author zhouchunjie
 * @date 2019/7/5 20:19
 */
object GithubServiceImpl : GithubService {

    private val rxHttp = RxHttp.Builder()
        .debug("HttpEngine", LogInterceptor.LogLevel.BODY)
        .baseUrl(GithubApi.BASE_URL)
        .build()

    private inline fun <reified T> RequestBuilder.execute(): Observable<T> {
        return this.toResultObservable(GithubConverter(T::class.java))
    }

    override fun login(username: String, password: String): Observable<UserInfo> {
        val authString = "$username:$password"
        val authorization = "Basic ${Base64.encodeToString(authString.toByteArray(), Base64.NO_WRAP)}"
        return rxHttp.get("${GithubApi.USER}$username")
            .addHeader("Authorization", authorization)
            .execute()
    }
}