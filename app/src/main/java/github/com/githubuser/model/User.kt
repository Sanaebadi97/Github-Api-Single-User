package github.com.githubuser.model

import com.squareup.moshi.Json

data class User(


    @Json(name = "name")
    val name: String,


    @Json(name = "blog")
    val webSite: String,

    @Json(name = "avatar_url")
    val avatarUrl: String,

    @Json(name = "bio")
    val bio: String


    )