package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;

@UsePlaywright(BaseTest.class)
public class ValidatePageTitleTest {

    private HomePage homePage;

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
        homePage = new HomePage(page);
    }

    @Test
    void pageTitleTest() {
        String title = homePage.getTitle();

        Assertions.assertTrue(title.contains("Practice Software Testing"));
    }
}
