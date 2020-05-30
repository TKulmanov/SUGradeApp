package su.app.domain

import androidx.lifecycle.ViewModel
import su.app.iternal.lazyDeferred
import su.app.repository.repositories.umkd.UmkdRepositoryImpl
import javax.inject.Inject

class UmkdViewModel @Inject constructor(
    umkdRepository: UmkdRepositoryImpl
) : ViewModel() {

    val disciplines by lazyDeferred {
        umkdRepository.getDisciplines()
    }


}
