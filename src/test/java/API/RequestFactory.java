package API;

import com.microsoft.playwright.Page;
import pages.BasePage;

public class RequestFactory extends BasePage {

    public RequestFactory(Page page) {
        super(page);
    }

    public void waitForSortedProducts() {
        page.waitForResponse("**/products?sort**",
                () -> {
                    page.getByTestId("sort").selectOption("Price (High - Low)");
                });
    }
}
