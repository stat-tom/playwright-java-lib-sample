package API;

import com.microsoft.playwright.Page;
import pages.BasePage;

public class ApiRequestFactory extends BasePage {

    public ApiRequestFactory(Page page) {
        super(page);
    }

    public void waitForSortedProducts() {
        page.waitForResponse("**/products?sort**",
                () -> {
                    page.getByTestId("sort").selectOption("Price (High - Low)");
                });
    }


}
