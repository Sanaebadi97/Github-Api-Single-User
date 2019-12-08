package github.com.githubuser.model

import com.google.gson.annotations.SerializedName

data class User(


    @SerializedName("name")
    val name: String,


    @SerializedName("blog")
    val webSite: String,

    @SerializedName("avatar_url")
    val avatarUrl: String,

    @SerializedName("bio")
    val bio: String



)