package com.senierr.base.support.utils

import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.math.BigDecimal

/**
 * 文件工具类
 *
 * @author zhouchunjie
 * @date 2017/4/21
 */
object FileUtil {

    /**
     * 获取文件的byte[]
     */
    fun getBytes(file: File?): ByteArray? {
        if (file == null || !file.exists()) return null
        var fis: FileInputStream? = null
        var bos: ByteArrayOutputStream? = null
        try {
            fis = FileInputStream(file)
            bos = ByteArrayOutputStream()
            val b = ByteArray(1024)
            var n: Int
            while (fis.read(b).also { n = it } != -1) {
                bos.write(b, 0, n)
            }
            return bos.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            CloseUtil.closeIOQuietly(fis, bos)
        }
        return null
    }

    /**
     * 获取文件/文件夹的大小
     */
    fun getFileSize(file: File?): Long {
        var size: Long = 0
        if (file == null || !file.exists()) return size
        if (file.isDirectory) {
            val fileList = file.listFiles()
            if (fileList != null && fileList.isNotEmpty()) {
                for (temp in fileList) {
                    size += getFileSize(temp)
                }
            }
        } else {
            size += file.length()
        }
        return size
    }

    /**
     * 格式化单位
     */
    fun getFormatSize(size: Double): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return "0K"
        }

        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val result1 = BigDecimal(java.lang.Double.toString(kiloByte))
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "KB"
        }

        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val result2 = BigDecimal(java.lang.Double.toString(megaByte))
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "MB"
        }

        val teraBytes = gigaByte / 1024
        if (teraBytes < 1) {
            val result3 = BigDecimal(java.lang.Double.toString(gigaByte))
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "GB"
        }
        val result4 = BigDecimal(teraBytes)
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }
}
