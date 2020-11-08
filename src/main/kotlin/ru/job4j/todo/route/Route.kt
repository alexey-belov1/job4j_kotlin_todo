package ru.job4j.todo.route

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.job4j.todo.control.AuthenticationController
import ru.job4j.todo.control.ItemController
import ru.job4j.todo.control.UserController
import ru.job4j.todo.filter.AuthorizationFilter
import spark.Spark.*

@Component
class Route(
    @Autowired private val userController : UserController,
    @Autowired private val authenticationController : AuthenticationController,
    @Autowired private val itemController : ItemController,
    @Autowired private val authorizationFilter : AuthorizationFilter
) {

    fun init() {
        post("/login", authenticationController.attempt)

        path("/user") {
            post("/sign-up", userController.create)
            path("/*") {
                before("/*", authorizationFilter.doFilter)
                get("/", userController.findAll)
            }
        }

        path("/item") {
            before("/*", authorizationFilter.doFilter)
            get("/", itemController.findAll)
            get("/:id", itemController.findById)
            post("/", itemController.create)
            put("/", itemController.update)
            delete("/:id", itemController.delete)
        }
    }
}