package ru.job4j.todo.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ru.job4j.todo.model.User
import ru.job4j.todo.repository.UserRepository

@Service
class UserService(@Autowired private val userRepository : UserRepository) {

    fun create(user : User) : User? =
        if(userRepository.findByName(user.name) == null) userRepository.create(user) else null

    fun findAll(): List<User> = userRepository.findAll()

    fun findByName(name : String): User? = userRepository.findByName(name)
}