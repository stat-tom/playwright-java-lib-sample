package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class ValidateLoginTest {

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com/auth/login");
    }

    @Test
    void shouldShowEmailAndPasswordValidationError(Page page) {
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#email-error")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#password-error")).isVisible();
    }

    @Test
    void shouldShowEmailValidationError(Page page) {
        page.locator("#password").fill("topSecret");
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#email-error")).isVisible();
    }

    @Test
    void shouldShowPasswordValidationError(Page page) {
        page.locator("#email").fill("a@b.c");
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#password-error")).isVisible();
    }

    @Test
    void shouldShowPasswordTest(Page page) {
        page.locator("#password").fill("topSecret");
        page.locator(".input-group-append").click();

        String passwordText = page.locator("#password").inputValue();
        Assertions.assertEquals("topSecret", passwordText, "Text content does not match!");
    }

    @Test
    void shouldOpenSignUpTest(Page page) {
        page.getByTestId("register-link").click();
        String actualText = page.locator("h3").innerText();

        Assertions.assertEquals("Customer registration", actualText, "Text content does not match!");
    }

    @Test
    void shouldOpenForgotPasswordTest(Page page) {
        page.getByTestId("forgot-password-link").click();
        String actualText = page.locator("h3").innerText();

        Assertions.assertEquals("Forgot Password", actualText, "Text content does not match!");
    }
}
