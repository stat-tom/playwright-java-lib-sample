package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@UsePlaywright(BaseTest.class)
public class ValidatePageTitleTest {

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
    }

    @Test
    void shouldShowThePageTitle(Page page) {
        String title = page.title();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }
}
