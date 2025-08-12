package ui.tests.components

import com.codeborne.selenide.Condition.exactText
import com.codeborne.selenide.Condition.matchText

import ui.BaseTestCase
import ui.pages.MainPage

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

class CheckGenerateClientButtonTest : BaseTestCase() {

    private val page = MainPage()

    @BeforeAll
    fun init() {
        page.openMain()
    }

    @Test
    @DisplayName("Click on client generate button")
    fun clickClientButton() {
        page.clickGenerateClientButton()
        page.shouldHaveText(page.randomOutClient, page.randomOutClientText)
    }

    @Test
    @DisplayName("Click on client generate button two times, check new value")
    fun clickClientButtonSecondTime() {
        val out = page.randomOutClient

        page.clickGenerateClientButton()
        out.shouldHave(matchText(page.randomOutClientText))

        val first = out.text

        page.clickGenerateClientButton()
        out.shouldNotHave(exactText(first))
    }
}