package ru.job4j.todo.repository

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.job4j.todo.model.Item
import ru.job4j.todo.repository.tables.Items
import java.time.LocalDateTime

@Service
class ItemRepository(@Autowired val db: Database) {

    fun create(item : Item) : Item = transaction(db) {
        item.apply {
            id = Items.insert {
                it[desc] = item.desc
                it[done] = item.done
                it[userId] = item.userId
                it[created] = item.created!!
            } get Items.id
        }
    }

    fun update(item : Item) : Int = transaction(db) {
        Items.update({ Items.id eq item.id}) {
            it[desc] = item.desc
            it[done] = item.done
        }
    }

    fun delete(id: Int) : Int = transaction(db) {
        Items.deleteWhere { Items.id eq id }
    }

    fun findById(id: Int) : Item? = transaction(db) {
        Items.select { Items.id eq id }.map {
            Item(it[Items.id], it[Items.desc], it[Items.done], it[Items.userId], it[Items.created])
        }.singleOrNull()
    }

    fun findAll() : List<Item> = transaction(db) {
        Items.selectAll().map {
            Item(it[Items.id], it[Items.desc], it[Items.done], it[Items.userId], it[Items.created])
        }
    }
}