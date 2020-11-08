package ru.job4j.todo.control

import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import ru.job4j.todo.service.ItemService
import ru.job4j.todo.model.Item
import ru.job4j.todo.model.User
import spark.Request
import spark.Response
import java.time.LocalDateTime

@Controller
class ItemController (@Autowired private val itemService : ItemService) {

    val create = {req : Request, resp : Response ->
        var item = Gson().fromJson(req.body(), Item::class.java).apply {
            userId = req.session().attribute<User>("user").id
            created = LocalDateTime.now()
        }
        resp.status(201)
        Gson().toJson(itemService.create(item))
    }

    val update = { req : Request, resp : Response ->
        val item = Gson().fromJson(req.body(), Item::class.java)
        if (itemService.update(item)) {
            resp.status(202)
            "Item successful updated"
        } else {
            resp.status(404)
            "Item not found"
        }
    }

    val delete = { req : Request, resp : Response ->
        val id = req.params("id").toInt()
        if (itemService.delete(id)) {
            resp.status(202)
            "Item successful deleted"
        } else {
            resp.status(404)
            "Item not found"
        }
    }

    val findById = { req : Request, resp : Response ->
        val item = itemService.findById(req.params("id").toInt())
        if (item != null) {
            resp.status(200)
            Gson().toJson(item)
        } else {
            resp.status(404)
            Gson().toJson(Item())
        }
    }

    val findAll = { req : Request, resp : Response ->
        resp.status(200)
        Gson().toJson(itemService.findAll())
    }
}