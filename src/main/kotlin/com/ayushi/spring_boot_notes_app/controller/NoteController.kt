package com.ayushi.spring_boot_notes_app.controller

import com.ayushi.spring_boot_notes_app.database.model.Note
import com.ayushi.spring_boot_notes_app.database.repository.NoteRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

// will have those functions which will be called when a mobile app will make a rest request or api request
// POST http://localhost:8080/notes
// GET http://localhost:8080/notes?ownerId=123
// DELETE http://localhost:8080/notes/123

@RestController
@RequestMapping("/notes")
class NoteController(
    private val repository: NoteRepository
) {
    data class NoteRequest(
        val id: String?,
        val title: String,
        val content: String,
        val color: Long
    )

    data class NoteResponse(
        val id: String,
        val title: String,
        val content: String,
        val color: Long,
        val createdAt: Instant
    )

    @PostMapping
    fun saveNote(
       @RequestBody body: NoteRequest
    ): NoteResponse {
        val note = repository.save(
            Note(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                content = body.content,
                color = body.color,
                createdAt = Instant.now(),
                ownerId = ObjectId()
            )
        )
        return note.toResponse()
    }

    @GetMapping
    fun findByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<NoteResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map {
            it.toResponse()
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteById(@PathVariable id: String){
         repository.deleteById(ObjectId(id))
    }
}

// mapper for mapping the response
private fun Note.toResponse(): NoteController.NoteResponse {
    return NoteController.NoteResponse(
        id = id?.toHexString() ?: "",
        title = title,
        content = content,
        color = color,
        createdAt = createdAt
    )
}