package su.app.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import su.app.repository.model.User
import su.app.repository.model.evaluation.attestation.Attestation
import su.app.repository.model.journal.Journal
import su.app.repository.model.evaluation.transcript.SemesterDataConverter
import su.app.repository.model.evaluation.transcript.SubjectDataConverter
import su.app.repository.model.evaluation.transcript.Transcript
import su.app.repository.model.journal.JournalDataConverter
import su.app.repository.model.notification.News
import su.app.repository.model.schedule.Exam
import su.app.repository.model.schedule.Schedule
import su.app.repository.room.dao.*

@Database(entities =
            [(Journal::class),
            (Attestation::class),
            (Transcript::class),
            (News::class),
            (Schedule::class),
                (Exam::class),
                (User::class)],
    version = 19,exportSchema = false)
@TypeConverters(JournalDataConverter::class, SemesterDataConverter::class, SubjectDataConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun journalDao(): JournalDao
    abstract fun scheduleDao(): ScheduleDao
    abstract fun examDao(): ExamDao
    abstract fun attestationDao(): AttestationDao
    abstract fun transcriptDao(): TranscriptDao
    abstract fun newsDao(): NewsDao
    abstract fun userDao(): UserDao
}