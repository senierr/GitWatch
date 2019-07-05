package com.senierr.repository.entity

import com.google.gson.annotations.SerializedName

/**
 * Github请求错误信息
 *
 * @author zhouchunjie
 * @date 2019/7/5 22:09
 */
data class Error(
    @SerializedName("message")
    var message: String? = ""
)