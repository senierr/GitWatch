package com.senierr.repository.entity.dto

/**
 * 异常返回
 */
data class HttpException(
    override val message: String = "UNKNOWN"
) : RuntimeException(message)