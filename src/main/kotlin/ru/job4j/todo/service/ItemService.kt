package ru.job4j.todo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.job4j.todo.model.Item
import ru.job4j.todo.repository.ItemRepository

@Component
class ItemService(@Autowired private val itemRepository : ItemRepository) {

    fun create(item : Item) : Item = itemRepository.create(item)

    fun update(item : Item): Boolean = itemRepository.update(item) > 0

    fun delete(id: Int) : Boolean = itemRepository.delete(id) > 0

    fun findById(id: Int) : Item? = itemRepository.findById(id)

    fun findAll(): List<Item> = itemRepository.findAll()
}