package su.app.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ActivityViewModelFactory @Inject constructor(private val ViewModelProvider: Provider<ViewModel>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ViewModelProvider.get() as T
    }

}