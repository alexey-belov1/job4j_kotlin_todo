package ru.job4j.todo.control

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.job4j.todo.service.UserService
import ru.job4j.todo.model.User
import spark.Request
import spark.Response

@Controller
class UserController(@Autowired private val userService : UserService) {

    val create = { req : Request, resp : Response ->
        var user : User = Gson().fromJson(req.body(), User::class.java)
        if (userService.create(user) != null) {
            resp.status(201)
            Gson().toJson(user)
        } else {
            resp.status(404)
            Gson().toJson(User())
        }

    }

    val findAll = { req : Request, resp : Response ->
        resp.status(200)
        Gson().toJson(userService.findAll())
    }
}