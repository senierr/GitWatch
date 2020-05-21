package com.senierr.repository.disk

import android.content.Context
import com.senierr.base.support.utils.DiskLruCacheUtil
import com.senierr.base.support.utils.StorageUtil
import java.io.File

/**
 * 磁盘管理
 *
 * @author zhouchunjie
 * @date 2019/11/28
 */
object DiskManager {

    private var diskLruCacheUtil: DiskLruCacheUtil? = null

    /**
     * 初始化
     */
    fun initialize(context: Context) {
        val destDir = StorageUtil.getCacheDir(context)
        val destFile = File(destDir, "diskLruCache")
        if (!destFile.exists()) destFile.mkdirs()
        diskLruCacheUtil = DiskLruCacheUtil.getInstance(destFile)
    }

    /**
     * 获取磁盘缓存
     */
    fun getDiskLruCache(): DiskLruCacheUtil? {
        return diskLruCacheUtil
    }

    /**
     * 获取磁盘缓存目录
     */
    fun getDiskCacheDir(context: Context): File {
        return StorageUtil.getCacheDir(context)
    }

    /**
     * 获取文件下载目录
     */
    fun getDownloadDir(context: Context): File {
        return StorageUtil.getDownloadDir(context)
    }
}