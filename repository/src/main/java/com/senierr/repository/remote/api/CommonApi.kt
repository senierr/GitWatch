package com.senierr.repository.remote.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

/**
 * 公共模块API
 *
 * @author zhouchunjie
 * @date 2020/5/26
 */
interface CommonApi {

    /**
     * 下载文件
     *
     * @param url 下载链接
     */
    @Streaming
    @GET
    fun downloadFile(@Url url: String?): Call<ResponseBody?>
}