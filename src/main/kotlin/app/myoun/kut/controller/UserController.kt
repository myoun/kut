package app.myoun.kut.controller

import app.myoun.kut.dto.PointDto
import app.myoun.kut.dto.UserDto
import app.myoun.kut.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @GetMapping("/users/user/{id}")
    fun getUser(@PathVariable("id") id : String): Any {
        return userService.getUserInfo(id) ?: mapOf("status" to 404, "message" to "cannot find user")
    }

    @PostMapping("/users/user")
    fun postUser(@RequestBody userDto: UserDto): Any {
        userService.createUser(userDto)
        return mapOf("status" to 200)
    }

    @PostMapping("/users/user/{id}/point")
    fun setPoint(@PathVariable("id") id: String, @RequestBody pointDto: PointDto) : Any {
        val isSuccess = userService.updatePoint(id, pointDto.point)
        return if (isSuccess) mapOf("status" to 200) else mapOf("status" to 404)
    }




}