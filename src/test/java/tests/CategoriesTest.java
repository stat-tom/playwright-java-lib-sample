package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.PowerToolsPage;
import pages.ProductPage;

@UsePlaywright(BaseTest.class)
public class CategoriesTest {

    private PowerToolsPage powerToolsPage;
    private ProductPage productPage;

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com/");
        powerToolsPage = new PowerToolsPage(page);
        productPage = new ProductPage(page);
    }

    @Test
    @Tag("smoke")
    void shouldFilterProductsByCategory() {
        powerToolsPage.clickOnPowerToolsCategory();

        var filteredNames = productPage.getProductNames();
        Assertions.assertThat(filteredNames).contains("Belt Sander", "Cordless Drill 18V", "Circular Saw");
    }


}
