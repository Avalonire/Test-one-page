package ui.tests.components

import com.codeborne.selenide.Condition.visible
import ui.BaseTestCase
import ui.pages.MainPage

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class CheckLoginTest : BaseTestCase() {

    private val page = MainPage()

    @BeforeAll
    fun init() {
        page.openMain()
    }

    @Test
    @DisplayName("Log in without credentials")
    fun logInWithout() {
        page.clickLoginButton()
        page.shouldHaveText(page.credentialsSquare, page.failedLoginText)
    }

    @Test
    @DisplayName("Type credentials")
    fun typeCredentials() {
        page.typeLogin(page.testLogin)
        page.typePassword(page.testPassword)
    }

    @Test
    @DisplayName("Log in with entered credentials")
    fun logInWith() {
        page.clickLoginButton()
        page.shouldHaveText(page.credentialsSquare, page.successfulLoginText)
        page.randomServerButton.shouldBe(visible)
    }

    @Test
    @DisplayName("Sign out")
    fun signOut() {
        page.clickSignOutButton()
        page.shouldHaveText(page.credentialsSquare, page.defaultSquareText)
        page.randomServerButton.shouldNotBe(visible)
    }
}