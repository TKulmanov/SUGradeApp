package su.app.repository.model


data class LoginResponse (
    var statusCode: Int,
    var authToken: String,
    var user: User
)