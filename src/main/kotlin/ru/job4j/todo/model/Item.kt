package ru.job4j.todo.model

import java.time.LocalDateTime

data class Item (
    var id : Int = 0,
    var desc : String = "",
    var done : Boolean = false,
    var userId : Int = 0,
    var created : LocalDateTime? = null
)