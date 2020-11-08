package ru.job4j.todo.repository.tables

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", length = 50).uniqueIndex()
    val password = varchar("password", length = 50)

    override val primaryKey = PrimaryKey(id)
}