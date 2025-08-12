package ui.tests.components

import com.codeborne.selenide.Condition.visible
import ui.BaseTestCase
import ui.pages.MainPage

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class FlowE2eTest : BaseTestCase() {

    private val page = MainPage()

    @BeforeAll
    fun initAndLogin() {
        page.openMain()
        page.typeLogin(page.testLogin)
        page.typePassword(page.testPassword)
        page.clickLoginButton()
        page.shouldHaveText(page.credentialsSquare, page.successfulLoginText)
    }

    @Test
    @DisplayName("Click on Server generate button")
    fun clickServerButton() {
        page.clickGenerateServerButton()
        page.shouldHaveText(page.randomOutServer, page.randomOutServerText)
    }


    @Test
    @DisplayName("Sign out")
    fun signOut() {
        page.clickSignOutButton()
        page.shouldHaveText(page.credentialsSquare, page.defaultSquareText)
        page.randomServerButton.shouldNotBe(visible)
    }
}