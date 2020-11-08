package ru.job4j.todo.repository

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction
import ru.job4j.todo.repository.tables.Items
import ru.job4j.todo.repository.tables.Users

object DatabaseFactory {

    fun init() {
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.apply {
                drop(Items, Users)
                create(Items, Users)
            }
        }
    }
}