package com.senierr.repository.remote

import com.google.gson.Gson
import com.senierr.base.support.utils.TypeUtil
import com.senierr.http.converter.Converter
import com.senierr.repository.remote.entity.HttpResponse
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.Type

/**
 * 响应解析
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:57
 */
class ResponseConverter<T>(private val types: Array<Type>) : Converter<T> {

    override fun convertResponse(response: Response): T {
        val responseBody = response.body() ?: throw IOException("ResponseBody is null!")
        val type = TypeUtil.parseType(HttpResponse::class.java, types)
        return Gson().fromJson(responseBody.string(), type)
    }
}