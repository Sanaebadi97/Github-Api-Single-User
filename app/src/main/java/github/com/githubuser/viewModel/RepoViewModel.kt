package github.com.githubuser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import github.com.githubuser.model.Repo
import github.com.githubuser.repository.Repository

class RepoViewModel : ViewModel() {
    private val _username: MutableLiveData<String> = MutableLiveData()

    val repos: LiveData<List<Repo>> = Transformations.switchMap(_username) { userId ->
        Repository.getRepoes(userId)


    }


    fun setUsername(userId: String) {
        if (_username.value == userId) {
            return
        }
        _username.value = userId

    }


    fun cancelJob() {
        Repository.cancelJob()
    }
}