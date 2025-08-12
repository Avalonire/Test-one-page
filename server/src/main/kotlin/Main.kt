import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.http.content.staticResources
import io.ktor.server.engine.embeddedServer
import io.ktor.server.application.*
import io.ktor.server.netty.Netty
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.response.respond
import io.ktor.server.response.respondRedirect

import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json

import kotlinx.serialization.Serializable


@Serializable
data class RanNumResponse(val number: Int)

fun Application.module() {
    install(ContentNegotiation) { json() }
    install(StatusPages) {
        exception<IllegalArgumentException> { call, e ->
            call.respond(HttpStatusCode.BadRequest, e.message ?: "Invalid input")
        }
    }

    routing {
        staticResources("/static", "static")
        get("/") { call.respondRedirect("/static/main.html") }
        get("/api/random") {
            val value = RandomGenerator.generate()
            call.respond(RanNumResponse(value))
        }
    }
}

fun main() {
    embeddedServer(Netty, port = System.getenv("PORT")?.toIntOrNull() ?: 8081) { module() }.start(wait = true)
}