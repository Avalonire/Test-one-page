package ui

import com.codeborne.selenide.Configuration
import com.codeborne.selenide.Selenide
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.junit.jupiter.api.*
import module

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTestCase {
    private var server: ApplicationEngine? = null

    @BeforeAll
    fun setupAll() {
        Configuration.browser = System.getProperty("selenide.browser", "firefox")
        Configuration.headless = System.getProperty("selenide.headless", "true").toBoolean()
        Configuration.timeout = 10000
        Configuration.browserSize = "1280x900"

        Configuration.baseUrl = "http://localhost:8081"   // ← вот так
        server = embeddedServer(Netty, host = "localhost", port = 8081) { module() }.start(false)
    }

    @AfterAll
    fun tearDownAll() {
        Selenide.closeWebDriver()
        server?.stop()
    }
}