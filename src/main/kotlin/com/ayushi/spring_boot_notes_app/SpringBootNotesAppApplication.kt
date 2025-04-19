package com.ayushi.spring_boot_notes_app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootNotesAppApplication

fun main(args: Array<String>) {
	runApplication<SpringBootNotesAppApplication>(*args)
}