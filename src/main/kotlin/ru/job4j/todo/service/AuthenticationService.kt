package ru.job4j.todo.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationService {

    private val SECRET = "SecretKeyToGenJWTs"
    private val EXPIRATION_TIME = 864000000 /* 10 days */

    fun createToken(name : String) : String =
        JWT.create()
            .withSubject(name)
            .withExpiresAt(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .sign(Algorithm.HMAC512(SECRET.toByteArray()))

    fun getNameFromToken(token : String) : String =
        JWT.require(Algorithm.HMAC512(SECRET))
            .build()
            .verify(token)
            .subject
}