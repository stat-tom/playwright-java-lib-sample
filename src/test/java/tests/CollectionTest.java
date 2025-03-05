package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;

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

        Assertions.assertThat(itemNames).isNotEmpty();

        String actualItem = itemNames.get(2).trim();
        Assertions.assertThat(actualItem).isEqualTo("Bolt Cutters");

        Optional<String> foundItem = itemNames.stream()
                .map(String::trim)
                .filter(item -> item.equals("Thor Hammer"))
                .findFirst();
        Assertions.assertThat(foundItem).isPresent();
    }

    @Test
    void shouldShowOutOfStockItems(Page page) {
        List<String> oosProducts = page.locator(".card")
                .filter(new Locator.FilterOptions().setHas(page.getByText("Out of stock")))
                .getByTestId("product-name")
                .allTextContents();
        System.out.println(oosProducts);

        Assertions.assertThat(oosProducts).isNotEmpty();
    }

    @Test
    void allProductPricesShouldBeCorrectValues(Page page) {
        List<Double> prices = page.getByTestId("product-price")
                .allTextContents()
                .stream()
                .map(price -> Double.parseDouble(price.replace("$", "")))
                .toList();

        Assertions.assertThat(prices)
                .isNotEmpty()
                .allMatch(price -> price > 0, "Price should be greater than 0")
                .doesNotContain(0.0)
                .allSatisfy(price -> Assertions.assertThat(price).isBetween(0.0, 1000.0));

        prices.forEach(price -> {
            Assertions.assertThat(String.format("%.2f", price))
                    .as("Price format is incorrect")
                    .matches("\\d{1,3}\\.\\d{2}");
        });
    }
}
