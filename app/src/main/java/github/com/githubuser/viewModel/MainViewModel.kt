package github.com.githubuser.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import github.com.githubuser.model.User
import github.com.githubuser.repository.Repository

class MainViewModel : ViewModel() {

    private val _username: MutableLiveData<String> = MutableLiveData()

    val user: LiveData<User> = Transformations.switchMap(_username) { userId ->
        Repository.getUser(userId)


    }


    val _name: MutableLiveData<String> = MutableLiveData()

    val name: LiveData<String>
        get() = _name


    private val _website: MutableLiveData<String> = MutableLiveData()

    val website: LiveData<String>
        get() = _website


    private val _bio: MutableLiveData<String> = MutableLiveData()

    val bio: LiveData<String>
        get() = _bio


    private val _image: MutableLiveData<String> = MutableLiveData()

    val image: LiveData<String>
        get() = _image


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
