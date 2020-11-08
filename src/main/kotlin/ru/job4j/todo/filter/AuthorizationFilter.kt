package ru.job4j.todo.filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.job4j.todo.service.AuthenticationService
import ru.job4j.todo.service.UserService
import spark.Request
import spark.Response
import spark.Spark.halt

@Component
class AuthorizationFilter(
    @Autowired private val authenticationService : AuthenticationService,
    @Autowired private val userService : UserService
) {

    private val HEADER_STRING = "Authorization"

    val doFilter = { req : Request, resp : Response ->
        val token = req.headers(HEADER_STRING)
        if (token == null ) {
            halt(404, "Token not found")
        }
        val userName = authenticationService.getNameFromToken(token)
        req.session().attribute("user", userService.findByName(userName))
    }
}