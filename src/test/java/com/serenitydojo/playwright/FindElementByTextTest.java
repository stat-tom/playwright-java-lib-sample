package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class FindElementByTextTest {

    @Test
    void shouldShowElementByText(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.getByText("Bolt Cutters").click();

        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void shouldShowElementByAltText(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.getByAltText("Long Nose Pliers").click();

        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void shouldShowElementByTitle(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.getByAltText("Slip Joint Pliers").click();
        page.getByTitle("Practice Software Testing - Toolshop").click();

        PlaywrightAssertions.assertThat(page.getByText("Price Range")).isVisible();
    }
}
