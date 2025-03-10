package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.AriaRole;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.NavigationBar;
import pages.PowerToolsPage;
import pages.ProductPage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

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
    void shouldDisplayToasterMessage(Page page) {
        homePage.selectProduct("Bolt Cutters");
        productPage.addToCart();

        assertThat(page.getByRole(AriaRole.ALERT)).isVisible();
        assertThat(page.getByRole(AriaRole.ALERT)).hasText("Product added to shopping cart.");

        page.waitForCondition(() -> navigationBar.getCartQty().equals("1"));
//        page.waitForSelector("[data-test=cart-quantity]:has-text('1')");
    }


}
