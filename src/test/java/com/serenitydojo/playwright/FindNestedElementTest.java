package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class FindNestedElementTest {

    @Test
    void shouldShowMenuElement(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.getByRole(AriaRole.MENUBAR, new Page.GetByRoleOptions().setName("Main Menu"))
                        .getByText("Categories")
                                .click();
        page.locator(".dropdown-menu").getByText("Power Tools").click();

        String actualText = page.getByTestId("page-title").innerText();
        Assertions.assertEquals("Category: Power Tools", actualText, "Text content does not match!");
    }
}
