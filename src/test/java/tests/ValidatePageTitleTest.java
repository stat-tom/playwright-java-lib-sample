package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BasePage;

@UsePlaywright(BaseTest.class)
public class ValidatePageTitleTest {

    private BasePage basePage;

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
        basePage = new BasePage(page);
    }

    @Test
    void pageTitleTest() {
        String title = basePage.getTitle();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }
}
