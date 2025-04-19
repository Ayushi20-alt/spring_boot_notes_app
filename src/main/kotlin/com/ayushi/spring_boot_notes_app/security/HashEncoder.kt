package com.ayushi.spring_boot_notes_app.security

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component // after annotating we are able to inject this instance to other class
class HashEncoder {

    private val bcrypt = BCryptPasswordEncoder()

    fun encode(raw : String) : String = bcrypt.encode(raw)

    fun matches(raw: String, hashed : String) : Boolean = bcrypt.matches(raw, hashed)
}