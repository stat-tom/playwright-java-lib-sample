package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Test;

public class ValidateLoginTest extends BaseTest {

    @Test
    void shouldShowValidationError() {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Sign in")).click();
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#email-error")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#password-error")).isVisible();
    }
}
