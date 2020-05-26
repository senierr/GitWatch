package com.senierr.repository.service.api

import java.io.File

/**
 * 公共服务
 *
 * @author zhouchunjie
 * @date 2020/5/25
 */
interface ICommonService {

    /**
     * 下载
     */
    suspend fun downloadFile(
        url: String,
        destName: String,
        listener: (totalSize: Long, currentSize: Long, percent: Int) -> Unit
    ): File
}