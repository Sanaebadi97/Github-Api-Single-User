package sanaebadi.info.montagithub.api

import retrofit2.http.GET
import retrofit2.http.Path
import sanaebadi.info.montagithub.model.User


//get the endpoint with path
interface ApiService {
    @GET("/users/{username}")
    suspend fun getUser(
        @Path("username") username: String
    ): User

}