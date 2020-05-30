package su.app.presentation.ui.journal.list

import android.view.View
import su.app.repository.model.journal.Journal

interface ItemClickListener {
    fun onItemClicked(journal: Journal,view: View)
}