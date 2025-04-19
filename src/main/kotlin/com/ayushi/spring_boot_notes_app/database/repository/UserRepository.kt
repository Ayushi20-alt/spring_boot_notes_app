package com.ayushi.spring_boot_notes_app.database.repository

import com.ayushi.spring_boot_notes_app.database.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<User, ObjectId>{
    fun findByUsername(username : String) : User?
}