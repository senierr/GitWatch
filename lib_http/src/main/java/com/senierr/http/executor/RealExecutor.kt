package com.senierr.http.executor

import com.senierr.http.converter.Converter
import com.senierr.http.progress.ProgressRequestBody
import com.senierr.http.progress.ProgressResponseBody
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.*
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * 执行器
 *
 * @author zhouchunjie
 * @date 2020/5/19
 */
class RealExecutor(
    private val okHttpClient: OkHttpClient,
    private val request: Request
) {

    /**
     * 同步
     */
    fun execute(): Response {
        return okHttpClient.newCall(request).execute()
    }

    /**
     * 挂起
     */
    suspend fun <T> execute(
        converter: Converter<T>,
        uploadListener: ((totalSize: Long, currentSize: Long, percent: Int) -> Unit)?,
        downloadListener: ((totalSize: Long, currentSize: Long, percent: Int) -> Unit)?
    ): T {
        return suspendCancellableCoroutine { continuation ->
            // 封装请求
            val realRequest = if (uploadListener != null) {
                var requestBody = request.body
                if (requestBody != null) {
                    requestBody =
                        ProgressRequestBody(requestBody) { totalSize, currentSize, percent ->
                            if (continuation.isActive) uploadListener.invoke(
                                totalSize,
                                currentSize,
                                percent
                            )
                        }
                }
                request.newBuilder().method(request.method, requestBody).build()
            } else {
                request
            }
            val call = okHttpClient.newCall(realRequest)

            // 监听协程取消
            continuation.invokeOnCancellation {
                call.cancel()
            }

            // 执行请求
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    // 封装返回
                    val realResponse = if (downloadListener != null) {
                        var responseBody = response.body
                        if (responseBody != null) {
                            responseBody =
                                ProgressResponseBody(responseBody) { totalSize, currentSize, percent ->
                                    if (continuation.isActive) downloadListener.invoke(
                                        totalSize,
                                        currentSize,
                                        percent
                                    )
                                }
                        }
                        response.newBuilder().body(responseBody).build()
                    } else {
                        response
                    }
                    // 解析结果
                    try {
                        val t = converter.convertResponse(realResponse)
                        continuation.resume(t)
                    } catch (e: Exception) {
                        continuation.resumeWithException(e)
                    }
                }
            })
        }
    }
}