package com.ayushi.spring_boot_notes_app.database.repository

import com.ayushi.spring_boot_notes_app.database.model.RefreshToken
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface RefreshTokenRepository : MongoRepository<RefreshToken, ObjectId>{

    fun findByUserIdAndHashedToken(userId: ObjectId, hashedToken: String): RefreshToken?
    // so that we delete the old reference of the previous refresh token
    fun deleteByUserIdAndHashedToken(userId: ObjectId, hashedToken: String)
}