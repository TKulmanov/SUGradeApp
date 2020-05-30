package su.app.presentation.ui.evaluation.transcript

import su.app.repository.model.evaluation.transcript.Semester

interface InfoClickListener {
    fun onInfoClicked(semester: Semester)
}