package com.senierr.repository.remote

import com.google.gson.Gson
import com.senierr.repository.entity.Error
import com.senierr.http.converter.Converter
import okhttp3.Response

/**
 * Github响应解析
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:57
 */
class GithubConverter<T>(private val clazz: Class<T>) : Converter<T> {

    override fun convertResponse(response: Response): T {
        if (!response.isSuccessful) {
            val error = Gson().fromJson<Error>(response.body()?.string(), Error::class.java)
            throw Exception(error.message)
        }
        return Gson().fromJson<T>(response.body()?.string(), clazz)
    }
}