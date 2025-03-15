package tests;

import API.MockRequestFactory;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Route;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(BaseTest.class)
public class RestApiTest {

    @Nested
    class MockingApiResponses {

        @BeforeEach
        void setUp(Page page) {
            BaseTest.openPage(page, "https://practicesoftwaretesting.com");
        }

        @Test
        void whenASingleItemIsFound(Page page) {
            page.route("**/products/search?q=Pliers", route -> {
                route.fulfill(
                        new Route.FulfillOptions()
                                .setBody(MockRequestFactory.RESPONSE_WITH_A_SINGLE_ENTRY)
                                .setStatus(200)
                );
            });

            page.getByPlaceholder("Search").fill("Pliers");
            page.getByPlaceholder("Search").press("Enter");

            assertThat(page.getByTestId("product-name")).hasCount(1);
            assertThat(page.getByTestId("product-name")).hasText("Super Pliers");
        }

        @Test
        void whenNoItemsAreFound(Page page) {
            page.route("**/products/search?q=Pliers", route -> {
                route.fulfill(
                        new Route.FulfillOptions()
                                .setBody(MockRequestFactory.RESPONSE_WITH_NO_ENTRIES)
                                .setStatus(200)
                );
            });

            page.getByPlaceholder("Search").fill("Pliers");
            page.getByPlaceholder("Search").press("Enter");

            assertThat(page.getByTestId("product-name")).hasCount(0);
            assertThat(page.getByTestId("search_completed")).hasText("There are no products found.");
        }
    }
}
