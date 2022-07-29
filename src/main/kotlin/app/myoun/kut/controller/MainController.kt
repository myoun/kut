package app.myoun.kut.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MainController {

    @GetMapping("/")
    @Operation(
        responses = [ApiResponse(responseCode = "200",
        content = [Content(mediaType = "text/plain", schema = Schema(implementation = String::class))])]
    )
    fun getIndex(): String {
        return "Kut v1"
    }
}