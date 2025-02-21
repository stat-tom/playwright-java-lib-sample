package com.serenitydojo.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidatePageTitleTest extends BaseTest {

    @Test
    void shouldShowThePageTitle() {
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }
}
