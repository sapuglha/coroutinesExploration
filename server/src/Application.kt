package com.sapuglha.server

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.Compression
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.put
import io.ktor.routing.routing
import java.text.DateFormat
import java.util.*

data class User(
    val id: String?,
    val username: String,
    val firstname: String,
    val lastname: String
)

fun Application.main() {
    install(DefaultHeaders)
    install(Compression)
    install(CallLogging)

    install(ContentNegotiation) {
        gson {
            setDateFormat(DateFormat.LONG)
            setPrettyPrinting()
        }
    }

    val userList: MutableList<User> = mutableListOf()

    routing {
        get("/users") {
            call.respond(userList)
        }

        get("/user/{id}") {
            val item = userList.firstOrNull { it.id == call.parameters["id"] }

            call.respond(item ?: HttpStatusCode.NotFound)
        }

        put("/user") {
            val input = call.receive<User>()

            userList.add(
                User(
                    id = UUID.randomUUID().toString(),
                    username = input.username,
                    firstname = input.firstname,
                    lastname = input.lastname
                )
            )

            call.respond(HttpStatusCode.Created)
        }

        post("/user") {
            val input = call.receive<User>()

            val existingUser = userList.firstOrNull { it.id == input.id }

            existingUser?.let { leUser ->
                userList.remove(leUser)
                userList.add(input)

                call.respond(HttpStatusCode.OK)

            } ?: call.respond(HttpStatusCode.NotFound)
        }
    }
}
