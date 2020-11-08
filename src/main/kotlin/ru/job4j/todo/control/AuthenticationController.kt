package ru.job4j.todo.control

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.job4j.todo.service.AuthenticationService
import ru.job4j.todo.repository.UserRepository
import ru.job4j.todo.model.User
import spark.Request
import spark.Response

@Controller
class AuthenticationController(
    @Autowired private val userService : UserRepository,
    @Autowired private val authenticationService : AuthenticationService
) {

    val attempt =  { req : Request, resp : Response ->
        val user = Gson().fromJson(req.body(), User::class.java)
        val userDB = userService.findByName(user.name)
        if (userDB != null && userDB.password == user.password) {
            resp.status(200)
            "Successful authentication. Token: ${authenticationService.createToken(user.name)}"
        } else {
            resp.status(404)
            "User not found"
        }
    }
}