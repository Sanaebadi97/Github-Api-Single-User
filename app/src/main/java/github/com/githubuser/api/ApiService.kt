package github.com.githubuser.api

import github.com.githubuser.model.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import github.com.githubuser.model.User


//get the endpoint with path
interface ApiService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): User


    @GET("/users/{username}/repos")
    suspend fun getRepoes(
        @Path("username") username: String
    ): List<Repo>


}