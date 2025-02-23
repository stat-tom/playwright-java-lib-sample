package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class ValidatePageTitleTest {

    @Test
    void shouldShowThePageTitle(Page page) {
        page.navigate("https://practicesoftwaretesting.com");
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }
}
