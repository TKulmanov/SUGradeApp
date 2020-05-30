package su.app.presentation.ui.umkd.file.list

import android.view.View
import su.app.repository.model.umkd.File

interface FileClickListener {
    fun onItemClicked(files:File, view: View)
}