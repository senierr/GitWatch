package com.senierr.repository.remote.entity

/**
 * 文章
 *
 * @author zhouchunjie
 * @date 2019/7/10 19:29
 */
class Article(
    var apkLink: String? = null,
    var author: String? = null,
    var chapterId: Int? = null,
    var chapterName: String? = null,
    var collect: Boolean? = null,
    var courseId: Int? = null,
    var desc: String? = null,
    var envelopePic: String? = null,
    var fresh: Boolean? = null,
    var id: Int? = null,
    var link: String? = null,
    var niceDate: String? = null,
    var origin: String? = null,
    var prefix: String? = null,
    var projectLink: String? = null,
    var publishTime: Long? = null,
    var superChapterId: Int? = null,
    var superChapterName: String? = null,
    var tags: List<Tag?>? = null,
    var title: String? = null,
    var type: Int? = null,
    var userId: Int? = null,
    var visible: Int? = null,
    var zan: Int? = null
)