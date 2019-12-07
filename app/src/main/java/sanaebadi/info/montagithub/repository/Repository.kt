package sanaebadi.info.montagithub.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import sanaebadi.info.montagithub.api.ApiService
import sanaebadi.info.montagithub.api.RetrofitClient
import sanaebadi.info.montagithub.model.User

object Repository {
    var job: CompletableJob? = null

    fun getUser(username: String): LiveData<User> {
        job = Job()

        return object : LiveData<User>() {
            override fun onActive() {
                super.onActive()
                job?.let { theJob ->
                    CoroutineScope(IO + theJob).launch {
                        val user = RetrofitClient.createWebAPI<ApiService>().getUser(username)
                        withContext(Main) {
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }


    }

    fun cancelJob() {
        job?.cancel()
    }
}