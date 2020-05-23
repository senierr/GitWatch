package com.senierr.github.utils

import android.content.Context
import com.senierr.github.R
import java.util.*

/**
 * 时间格式化工具
 *
 * @author zhouchunjie
 * @date 2020/5/23 10:12
 */
object DateFormatUtil {

    private const val minute = 60 * 1000L   // 1分钟
    private const val hour = 60 * minute    // 1小时
    private const val day = 24 * hour       // 1天

    /**
     * 获取通用日期格式
     */
    fun getNormalDate(context: Context, mills: Long): String {
        val diff: Long = Date().time - Date(mills).time
        if (diff > day) {
            return context.getString(R.string.format_date_day_ago, diff / day)
        }
        if (diff > hour) {
            return context.getString(R.string.format_date_hour_ago, diff / hour)
        }
        if (diff > minute) {
            return context.getString(R.string.format_date_minute_ago, diff / minute)
        }
        return context.getString(R.string.format_date_recent)
    }
}