package com.senierr.github.ext

/**
 * 字符串扩展方法
 *
 * @author zhouchunjie
 * @date 2020/5/23 18:51
 */

/**
 * 转换特殊字符
 */
fun String.translation(): String {
    return this.replace("&lt;", "<")
        .replace("&gt;", ">")
        .replace("&amp;", "&")
        .replace("&quot;", "\"")
        .replace("&copy;", "©")
        .replace("&ldquo;", "“")
        .replace("&rdquo;", "”")
        .replace("&mdash;", "-")
}