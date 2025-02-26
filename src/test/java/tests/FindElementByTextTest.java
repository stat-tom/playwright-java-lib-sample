package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class FindElementByTextTest {

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
    }

    @Test
    void shouldShowElementByText(Page page) {
        page.getByText("Bolt Cutters").click();

        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void shouldShowElementByAltText(Page page) {
        page.getByAltText("Long Nose Pliers").click();

        PlaywrightAssertions.assertThat(page.getByText("MightyCraft Hardware")).isVisible();
    }

    @Test
    void shouldShowElementByTitle(Page page) {
        page.getByAltText("Slip Joint Pliers").click();
        page.getByTitle("Practice Software Testing - Toolshop").click();

        PlaywrightAssertions.assertThat(page.getByText("Price Range")).isVisible();
    }
}
