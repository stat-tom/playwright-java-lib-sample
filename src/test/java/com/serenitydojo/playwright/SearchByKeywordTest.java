package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class SearchByKeywordTest {

    @Test
    void shouldShowMatchingResults(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        page.getByPlaceholder("Search").fill("pliers");
        page.locator("button:has-text('Search')").click();

        int matchingSearchResults = page.locator(".card").count();

        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
