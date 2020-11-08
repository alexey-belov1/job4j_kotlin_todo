package ru.job4j.todo.repository

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.job4j.todo.model.User
import ru.job4j.todo.repository.tables.Users

@Service
class UserRepository(@Autowired val db: Database) {

    fun create(user : User) : User = transaction(db) {
        user.apply {
            id = Users.insert {
                it[name] = user.name
                it[password] = user.password
            } get Users.id
        }
    }

    fun findByName(name: String) : User? = transaction(db) {
        Users.select { Users.name eq name }.map {
            User(it[Users.id], it[Users.name], it[Users.password])
        }.singleOrNull()
    }

    fun findAll() : List<User> = transaction(db) {
        Users.selectAll().map {
            User(it[Users.id], it[Users.name], it[Users.password])
        }
    }
}