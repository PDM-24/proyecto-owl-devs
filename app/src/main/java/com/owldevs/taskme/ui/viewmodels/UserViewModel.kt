import androidx.lifecycle.ViewModel
import com.owldevs.taskme.model.User

class UserViewModel : ViewModel() {
    var currentUser: User? = null
}