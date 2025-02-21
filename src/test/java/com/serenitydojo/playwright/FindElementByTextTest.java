package com.serenitydojo.playwright;

import com.microsoft.playwright.assertions.PlaywrightAssertions;
import org.junit.jupiter.api.Test;

public class FindElementByTextTest extends BaseTest {

    @Test
    void shouldShowElementByText() {
        page.getByText("Bolt Cutters").click();

        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void shouldShowElementByAltText() {
        page.getByAltText("Long Nose Pliers").click();

        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void shouldShowElementByTitle() {
        page.getByAltText("Slip Joint Pliers").click();
        page.getByTitle("Practice Software Testing - Toolshop").click();

        PlaywrightAssertions.assertThat(page.getByText("Price Range")).isVisible();
    }
}
