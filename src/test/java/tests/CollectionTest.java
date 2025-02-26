package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

@UsePlaywright(BaseTest.class)
public class CollectionTest {

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
    }

    @Test
    void shouldShowCollectionOfItems(Page page) {
        List<String> itemNames = page.getByTestId("product-name").allTextContents();
        System.out.println(itemNames);

        Assertions.assertFalse(itemNames.isEmpty(), "Expected at least 1 item in the collection");

        String actualItem = itemNames.get(2).trim();
        Assertions.assertEquals("Bolt Cutters", actualItem, "Third item should be 'Bolt Cutters'");

        Optional<String> foundItem = itemNames.stream()
                .map(String::trim)
                .filter(item -> item.equals("Thor Hammer"))
                .findFirst();
        Assertions.assertTrue(foundItem.isPresent(), "'Thor Hammer' should be present in the collection");
    }

    @Test
    void shouldShowOutOfStockItems(Page page) {
        List<String> oosProducts = page.locator(".card")
                .filter(new Locator.FilterOptions().setHas(page.getByText("Out of stock")))
                .getByTestId("product-name")
                .allTextContents();
        System.out.println(oosProducts);

        Assertions.assertFalse(oosProducts.isEmpty(), "Expected at least 1 out of stock item in the collection");
    }
}
