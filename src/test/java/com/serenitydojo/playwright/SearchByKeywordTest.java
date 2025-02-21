package com.serenitydojo.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//@UsePlaywright
public class SearchByKeywordTest extends BaseTest {

    @Test
    void shouldShowThePageTitle() {
        page.navigate("https://practicesoftwaretesting.com");
        page.locator("[placeholder=Search]").fill("pliers");
        page.locator("button:has-text('Search')").click();

        int matchingSearchResults = page.locator(".card").count();

        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
