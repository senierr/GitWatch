package com.senierr.repository.remote

import com.alibaba.fastjson.JSON
import com.senierr.http.converter.Converter
import com.senierr.repository.remote.entity.Error
import okhttp3.Response

/**
 * Github响应解析
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:57
 */
class GithubArrayConverter<T>(val clazz: Class<T>) : Converter<MutableList<T>> {

    override fun convertResponse(response: Response): MutableList<T> {
        if (!response.isSuccessful) {
            throw JSON.parseObject(response.body()?.string(), Error::class.java)
        }
        return JSON.parseArray(response.body()?.string(), clazz)
    }
}