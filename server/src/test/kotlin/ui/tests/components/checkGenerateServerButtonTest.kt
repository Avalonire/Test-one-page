package ui.tests.components

import com.codeborne.selenide.Condition.exactText
import com.codeborne.selenide.Condition.matchText

import ui.BaseTestCase
import ui.pages.MainPage

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class CheckGenerateButtonTest : BaseTestCase() {

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
    @DisplayName("Click on Server generate button two times, check new value")
    fun clickServerButtonSecondTime() {

        val out = page.randomOutServer

        page.clickGenerateServerButton()
        out.shouldHave(matchText(page.randomOutServerText))

        val first = out.text

        page.clickGenerateServerButton()
        out.shouldNotHave(exactText(first))
    }
}