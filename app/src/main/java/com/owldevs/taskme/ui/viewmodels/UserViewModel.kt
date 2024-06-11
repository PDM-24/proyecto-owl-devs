import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.owldevs.taskme.model.User

class UserViewModel : ViewModel() {

    private val _currentUser = MutableLiveData<User?>()
    val currentUser: LiveData<User?> = _currentUser

    fun setCurrentUser(user: User?) {
        _currentUser.value = user
    }

    fun changeUserRole(role: String) {
        _currentUser.value = _currentUser.value?.copy(role = role)
    }
}
