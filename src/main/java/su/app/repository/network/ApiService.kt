package su.app.repository.network

import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import su.app.repository.model.LoginResponse
import su.app.repository.model.LoginRequest
import su.app.repository.model.evaluation.attestation.Attestation
import su.app.repository.model.evaluation.transcript.Transcript
import su.app.repository.model.journal.Journal
import su.app.repository.model.notification.News
import su.app.repository.model.schedule.Exam
import su.app.repository.model.schedule.Schedule
import su.app.repository.model.umkd.Category
import su.app.repository.model.umkd.Discipline

interface ApiService {

    @POST("/auth/login")
    fun login(@Body request: LoginRequest): Deferred<LoginResponse>

    @GET("/journal")
    fun getJournal(): Deferred<List<Journal>>

    @GET("/schedule")
    fun getSchedule(): Deferred<List<Schedule>>

    @GET("/exam")
    fun getExam(): Deferred<List<Exam>>

    @GET("/attestation")
    fun getAttestation(): Deferred<List<Attestation>>

    @GET("/transcript")
    fun getTranscript(): Deferred<Transcript>

    @GET("/news")
    fun getNews(): Deferred<List<News>>

    @GET("/umkd")
    fun getDisciplines(): Deferred<List<Discipline>>

    @GET("/category")
    fun getCategories(@Query("disciplineId") disciplineId:String): Deferred<List<Category>>
}