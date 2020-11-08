package ru.job4j.todo.config

import org.jetbrains.exposed.sql.Database
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class ExposedConfig {

    @Bean
    open fun dataBase() : Database = Database.connect(
        url = "jdbc:postgresql://127.0.0.1:5432/kt_todo",
        user = "postgres",
        password = "password"
    )
}