package su.app.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.umkd.UmkdRepositoryImpl
import javax.inject.Inject

class CategoryViewModel @Inject constructor(
    umkdRepository:UmkdRepositoryImpl
) : ViewModel() {

    val disciplineId = MutableLiveData<String>()

    val categories by lazyDeferred {
        umkdRepository.getCategories(disciplineId.value!!)
    }
}
