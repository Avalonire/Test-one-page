package ui.tests.components

import com.codeborne.selenide.Condition.visible
import ui.BaseTestCase
import ui.pages.MainPage

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test


class CheckMainPageEleTest : BaseTestCase() {

    private val page = MainPage()

    @BeforeAll
    fun init() {
        page.openMain()
    }

    @Test
    @DisplayName("Check main page elements are visible")
    fun checkMainPageElements() {
        page.shouldHaveTitle("Simple Web by Avalon")
        page.randomClientButton.shouldBe(visible)
        page.loginButton.shouldBe(visible)
        page.signOutButton.shouldBe(visible)
    }
}