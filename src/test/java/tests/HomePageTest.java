package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.LoadState;
import org.junit.jupiter.api.*;
import org.assertj.core.api.Assertions;
import pages.HomePage;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@UsePlaywright(BaseTest.class)
public class HomePageTest {

    private HomePage homePage;

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com");
        page.waitForSelector(".card-img-top");
        homePage = new HomePage(page);
    }

    @Test
    void shouldShowAllProductsNames(Page page) {
        List<String> itemNames = page.getByTestId("product-name").allInnerTexts();
        Assertions.assertThat(itemNames).contains("Pliers", "Bolt Cutters", "Thor Hammer");
    }

    @Test
    void shouldShowAllProductsImages(Page page) {
        List<String> itemImageTitles = page.locator(".card-img-top")
                .all()
                .stream()
                .map(img -> img.getAttribute("alt"))
                .toList();

        Assertions.assertThat(itemImageTitles).contains("Pliers", "Bolt Cutters", "Thor Hammer");
    }

    @Test
    void shouldSortInAlphabeticalOrder(Page page) {
        homePage.sortAtoZ();
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<String> itemNames = page.getByTestId("product-name").allTextContents();

        Assertions.assertThat(itemNames)
                .isSorted()
                .isSortedAccordingTo(String.CASE_INSENSITIVE_ORDER);
    }

    @Test
    void shouldSortInReverseOrder(Page page) {
        homePage.sortZtoA();
        page.waitForLoadState(LoadState.NETWORKIDLE);

        List<String> itemNames = page.getByTestId("product-name").allTextContents();

        Assertions.assertThat(itemNames)
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    @Test
    void shouldShowCollectionOfItems(Page page) {
        List<String> itemNames = page.getByTestId("product-name").allInnerTexts();
        System.out.println(itemNames);

        Assertions.assertThat(itemNames).isNotEmpty();

        String actualItem = itemNames.get(2);
        Assertions.assertThat(actualItem).isEqualTo("Bolt Cutters");

        Optional<String> foundItem = itemNames.stream()
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
