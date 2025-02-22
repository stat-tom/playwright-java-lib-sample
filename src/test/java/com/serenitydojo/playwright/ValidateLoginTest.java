package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidateLoginTest extends BaseTest {

    @Test
    void shouldShowEmailAndPasswordValidationError() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#email-error")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#password-error")).isVisible();
    }

    @Test
    void shouldShowEmailValidationError() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.locator("#password").fill("topSecret");
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#email-error")).isVisible();
    }

    @Test
    void shouldShowPasswordValidationError() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.locator("#email").fill("a@b.c");
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#password-error")).isVisible();
    }

    @Test
    void shouldShowPasswordTest() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.locator("#password").fill("topSecret");
        page.locator(".input-group-append").click();

        String passwordText = page.locator("#password").inputValue();
        Assertions.assertEquals("topSecret", passwordText, "Text content does not match!");
    }

    @Test
    void shouldOpenSignUpTest() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByTestId("register-link").click();
        String actualText = page.locator("h3").innerText();

        Assertions.assertEquals("Customer registration", actualText, "Text content does not match!");
    }

    @Test
    void shouldOpenForgotPasswordTest() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.getByTestId("forgot-password-link").click();
        String actualText = page.locator("h3").innerText();

        Assertions.assertEquals("Forgot Password", actualText, "Text content does not match!");
    }
}
