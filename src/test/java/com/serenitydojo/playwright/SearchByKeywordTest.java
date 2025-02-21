package com.serenitydojo.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SearchByKeywordTest extends BaseTest {

    @Test
    void shouldShowMatchingResults() {
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();

        int matchingSearchResults = page.locator(".card").count();

        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
