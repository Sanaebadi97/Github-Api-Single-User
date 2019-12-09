package github.com.githubuser.model

import com.google.gson.annotations.SerializedName

data class Repo(

    @SerializedName("id")
    val repoId: Long,


    @SerializedName("name")
    val repoName: String,

    @SerializedName("description")
    val repoDesc: String,

    @SerializedName("url")
    val repoUrl: String
)