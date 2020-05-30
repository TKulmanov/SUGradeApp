package su.app.presentation.ui.umkd.category

import android.view.View
import su.app.repository.model.umkd.Category

interface CategoryClickListener {
    fun onItemClicked(category: Category, view: View)
}