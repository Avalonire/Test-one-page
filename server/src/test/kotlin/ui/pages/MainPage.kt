package ui.pages

import com.codeborne.selenide.SelenideElement
import com.codeborne.selenide.Condition.text
import com.codeborne.selenide.Condition.visible
import com.codeborne.selenide.Selenide

class MainPage {

    val title: SelenideElement get() = Selenide.element("h1")
    val randomClientButton: SelenideElement get() = Selenide.element("#randomClientButton")
    val randomServerButton: SelenideElement get() = Selenide.element("#randomServerButton")
    val randomOutClient: SelenideElement get() = Selenide.element("#randomOutClient")
    val randomOutServer: SelenideElement get() = Selenide.element("#randomOutServer")
    val loginInput: SelenideElement get() = Selenide.element("#login")
    val passwordInput: SelenideElement get() = Selenide.element("#password")
    val loginButton: SelenideElement get() = Selenide.element("#loginButton")
    val signOutButton: SelenideElement get() = Selenide.element("#signOutButton")
    val credentialsSquare: SelenideElement get() = Selenide.element("#credentials")

    val testLogin = "TestLogin"
    val testPassword = "TestPassword"
    val defaultSquareText = "Waiting..."
    val failedLoginText = "Please enter both\n" + "login and\n" + "password."
    val successfulLoginText = "Login Successful!\nYour Credentials:\nlogin: $testLogin\npassword: $testPassword"
    val randomOutClientText = "Client says: "
    val randomOutServerText = "Server says: "


    //Basics
    fun openMain(path: String = "/") = apply { Selenide.open(path) }

    // Checks
    fun shouldHaveTitle(expected: String): MainPage = apply {
        title.shouldHave(text(expected))
    }

    fun shouldHaveText(element: SelenideElement, expectedText: String): MainPage = apply {
        element.shouldBe(visible)
        element.shouldHave(text(expectedText))
    }


    // Clicks
    private fun clickVisibleEle(element: SelenideElement): MainPage = apply {
        element.shouldBe(visible)
        element.click()
    }

    fun clickLoginButton(): MainPage = clickVisibleEle(loginButton)
    fun clickSignOutButton(): MainPage = clickVisibleEle(signOutButton)
    fun clickGenerateClientButton(): MainPage = clickVisibleEle(randomClientButton)
    fun clickGenerateServerButton(): MainPage = clickVisibleEle(randomServerButton)

    // Types
    fun typeLogin(v: String): MainPage = apply {
        loginInput.shouldBe(visible)
        loginInput.value = v
    }

    fun typePassword(v: String): MainPage = apply {
        passwordInput.shouldBe(visible)
        passwordInput.value = v
    }

}
