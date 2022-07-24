package app.myoun.kut.utils

interface Response {
    val status: Int
}

class SuccessfulResponse(override val status: Int, val data: Any): Response
class FailedResponse(override val status: Int, val message: String): Response