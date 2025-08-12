package unit

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.DisplayName

import io.ktor.server.testing.testApplication

import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.withCharset

import kotlinx.serialization.json.Json
import module
import RanNumResponse

import kotlin.test.assertEquals
import kotlin.test.assertTrue

private const val PATH_ROOT = "/"
private const val PATH_RANDOM = "/api/random"


class ApiTest {

    @Test
    @DisplayName("GET root returns 200 Found")
    fun getRootRedirectsToIndex() = testApplication {
        application { module() }
        val res = client.get(PATH_ROOT)
        assertEquals(HttpStatusCode.OK, res.status)
    }

    @Test
    @DisplayName("GET api/random returns JSON")
    fun getRandom() = testApplication {
        application { module() }
        val res = client.get(PATH_RANDOM)
        assertEquals(HttpStatusCode.OK, res.status)
        assertEquals(ContentType.Application.Json.withCharset(Charsets.UTF_8), res.contentType())
        val dto = Json.decodeFromString<RanNumResponse>(res.bodyAsText())
        assertTrue(dto.number in 0 until 1000000)
    }
}