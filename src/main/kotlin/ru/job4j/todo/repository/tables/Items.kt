package ru.job4j.todo.repository.tables

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime

object Items : Table() {
    val id = integer("id").autoIncrement()
    val desc = varchar("description", 200)
    val done = bool("done")
    val userId = reference("user_id", Users.id)
    val created = datetime("createdAt")

    override val primaryKey = PrimaryKey(id)
}