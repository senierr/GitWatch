package com.senierr.repository.entity.dto

/**
 * 仓库
 */
data class Article(
    val apkLink: String = "",
    val audit: Int = 0,
    val author: String = "",
    val canEdit: Boolean = false,
    val chapterId: Long = 0,
    val chapterName: String = "",
    val collect: Boolean = false,
    val courseId: Long = 0,
    val desc: String = "",
    val descMd: String = "",
    val envelopePic: String = "",
    val fresh: Boolean = false,
    val id: Long = 0,
    val link: String = "",
    val niceDate: String = "",
    val niceShareDate: String = "",
    val origin: String = "",
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long = 0,
    val selfVisible: Int = 0,
    val shareDate: Long = 0,
    val shareUser: String = "",
    val superChapterId: Long = 0,
    val superChapterName: String = "",
    val tags: MutableList<Tag> = mutableListOf(),
    val title: String = "",
    val type: Int = 0,
    val userId: Long = 0,
    val visible: Int = 0,
    val zan: Int = 0
) {
    companion object {
        const val TYPE_NORMAL = 0   // 普通文章
        const val TYPE_TOP = 1      // 置顶文章
    }
}