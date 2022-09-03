package app.myoun.kut

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.RestControllerAdvice

@SpringBootApplication
@EnableJpaAuditing
class KutApplication

fun main(args: Array<String>) {
    runApplication<KutApplication>(*args)
}

@RestControllerAdvice
class ApiControllerAdvice {

    fun handleValidationExceptions(e: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val rawErrors = e.bindingResult.allErrors.map { (it as FieldError).field to it.defaultMessage!! }.toTypedArray()
        val errors = hashMapOf(*rawErrors)
        return ResponseEntity.badRequest().body(errors)
    }
}