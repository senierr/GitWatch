package com.senierr.repository.remote.entity

import com.alibaba.fastjson.annotation.JSONField

/**
 * Github请求错误信息
 *
 * @author zhouchunjie
 * @date 2019/7/5 22:09
 */
data class Error(
    @JSONField(name = "message")
    var errorMsg: String = ""
) : Exception()