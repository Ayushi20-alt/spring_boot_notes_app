package com.ayushi.spring_boot_notes_app.database.model

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("refresh_tokens")
data class RefreshToken(
    val userId : ObjectId,
    @Indexed(expireAfter = "0s") // this will delete my token entry when the refresh token expires we don't have to worry about deletion
    val expiredAt : Instant,
    val hashedToken: String,
    val createdAt : Instant = Instant.now()
)