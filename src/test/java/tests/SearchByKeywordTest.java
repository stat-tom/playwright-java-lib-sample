package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;

@UsePlaywright(BaseTest.class)
public class SearchByKeywordTest {

    private HomePage homePage;

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
        homePage = new HomePage(page);
    }

    @Test
    void shouldShowMatchingResults() {
        homePage.searchForProduct("pliers");
        int matchingSearchResults = homePage.getSearchResultsCount();

        Assertions.assertTrue(matchingSearchResults > 0);
    }
}
