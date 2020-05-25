package com.senierr.repository.service.impl

import com.senierr.base.support.utils.CloseUtil
import com.senierr.repository.disk.DiskManager
import com.senierr.repository.remote.RemoteManager
import com.senierr.repository.remote.progress.OnProgressListener
import com.senierr.repository.remote.progress.ProgressResponseBody
import com.senierr.repository.service.api.ICommonService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request
import okio.BufferedSink
import okio.BufferedSource
import okio.Okio
import java.io.File
import java.io.IOException

/**
 *
 * @author zhouchunjie
 * @date 2020/5/25
 */
class CommonService : ICommonService {

    override suspend fun download(
        url: String,
        destName: String,
        listener: (totalSize: Long, currentSize: Long, percent: Int) -> Unit
    ): File {
        return withContext(Dispatchers.IO) {
            val okHttpClient = RemoteManager.getOkHttpClient()
            val request = Request.Builder()
                .url(url)
                .build()
            val call = okHttpClient.newCall(request)
            val rawResponse = call.execute()

            // 封装进度
            var responseBody = rawResponse.body()
            if (responseBody != null) {
                responseBody = ProgressResponseBody(responseBody, object : OnProgressListener {
                    override fun onProgress(totalSize: Long, currentSize: Long, percent: Int) {
                        listener.invoke(totalSize, currentSize, percent)
                    }
                })
            }
            val response = rawResponse.newBuilder()
                .body(responseBody)
                .build()

            val destDir = DiskManager.getDownloadDir()
            // 判断路径是否存在
            if (!destDir.exists()) {
                val result = destDir.mkdirs()
                if (!result) {
                    throw Exception(destDir.path + " create failed!")
                }
            }

            val destFile = File(destDir, destName)
            // 判断文件是否存在
            if (destFile.exists()) {
                val result = destFile.delete()
                if (!result) {
                    throw Exception(destFile.path + " delete failed!")
                }
            }

            var bufferedSource: BufferedSource? = null
            var bufferedSink: BufferedSink? = null
            try {
                val rawResponseBody = response.body() ?: throw IOException("ResponseBody is null!")

                bufferedSource = Okio.buffer(Okio.source(rawResponseBody.byteStream()))
                bufferedSink = Okio.buffer(Okio.sink(destFile))

                val bytes = ByteArray(1024)
                var len: Int
                while (bufferedSource.read(bytes).also { len = it } != -1) {
                    bufferedSink.write(bytes, 0, len)
                }
                bufferedSink.flush()
                return@withContext destFile
            } finally {
                CloseUtil.closeIOQuietly(bufferedSource, bufferedSink)
            }
        }
    }
}