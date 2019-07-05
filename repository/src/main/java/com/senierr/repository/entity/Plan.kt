package com.senierr.repository.entity

import com.google.gson.annotations.SerializedName

/**
 * 用户计划信息
 *
 * @author zhouchunjie
 * @date 2019/7/5 21:56
 */
data class Plan(
    @SerializedName("collaborators")
    var collaborators: Int? = 0,
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("private_repos")
    var privateRepos: Int? = 0,
    @SerializedName("space")
    var space: Int? = 0
)