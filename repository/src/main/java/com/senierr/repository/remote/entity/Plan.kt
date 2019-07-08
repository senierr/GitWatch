package com.senierr.repository.remote.entity

import com.alibaba.fastjson.annotation.JSONField


/**
 * 用户计划信息
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:56
 */
data class Plan(
    @JSONField(name = "collaborators")
    var collaborators: Int = 0,
    @JSONField(name = "name")
    var name: String = "",
    @JSONField(name = "private_repos")
    var privateRepos: Int = 0,
    @JSONField(name = "space")
    var space: Int = 0
)