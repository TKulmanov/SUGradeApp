package su.app.presentation.ui.umkd

import android.view.View
import su.app.repository.model.umkd.Discipline

interface DisciplineClickListener {
    fun onItemClicked(disciplineId:String, view: View)
}