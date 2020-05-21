package com.senierr.repository.entity.dto
import com.google.gson.annotations.SerializedName


/**
 * 仓库
 */
data class Repo(
    val id: Long = 0,
    val name: String = "",
    @SerializedName("human_name")
    val humanName: String = "",
    @SerializedName("full_name")
    val fullName: String = "",
    val description: String = "",
    val language: String = "",
    @SerializedName("watchers_count")
    val watchersCount: Int = 0,
    @SerializedName("stargazers_count")
    val stargazersCount: Int = 0,
    @SerializedName("forks_count")
    val forksCount: Int = 0,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int = 0,
    @SerializedName("project_creator")
    val projectCreator: String = "",
    @SerializedName("html_url")
    val htmlUrl: String = "",
    @SerializedName("created_at")
    val createdAt: String = "",
    @SerializedName("updated_at")
    val updatedAt: String = ""
)