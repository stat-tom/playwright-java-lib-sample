package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.NavigationBar;
import pages.PowerToolsPage;
import pages.ProductPage;

@UsePlaywright(BaseTest.class)
public class BasketTest {

    private HomePage homePage;
    private ProductPage productPage;
    private NavigationBar navigationBar;

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com/");
        homePage = new HomePage(page);
        productPage = new ProductPage(page);
        navigationBar = new NavigationBar(page);
    }

    @Test
    @Tag("smoke")
    void shouldDisplayToasterMessage() {
        homePage.selectProduct("Bolt Cutters");
        productPage.addToCart();
    }


}
