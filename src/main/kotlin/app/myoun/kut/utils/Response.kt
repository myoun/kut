package app.myoun.kut.utils

data class ValidationResponse(val isValid: Boolean)
data class ErrorMessage(val messages: Collection<String>)