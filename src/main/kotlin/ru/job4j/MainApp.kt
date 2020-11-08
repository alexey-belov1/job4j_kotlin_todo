package ru.job4j

import org.springframework.context.annotation.AnnotationConfigApplicationContext
import ru.job4j.todo.route.Route
import ru.job4j.todo.repository.DatabaseFactory

fun main() {
    with(AnnotationConfigApplicationContext()) {
        scan("ru.job4j.todo")
        refresh()
        DatabaseFactory.init()
        getBean(Route::class.java).init()
    }
}