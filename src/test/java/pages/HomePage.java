package pages;

import com.microsoft.playwright.Page;

public class HomePage extends BasePage {

    public HomePage(Page page) {
        super(page);
    }

    public void clickOnProductByName(String productName) {
        page.getByText(productName).click();
    }

    public void searchForProduct(String keyword) {
        page.getByPlaceholder("Search").fill(keyword);
        page.locator("button:has-text('Search')").click();
    }

    public int getSearchResultsCount() {
        return page.locator(".card").count();
    }

    public String getProductTitle() {
        return page.getByTestId("page-title").innerText();
    }

    public String getTitle() {
        return page.title();
    }

    public void sortAZ() {
        page.getByLabel("Sort").selectOption("Name (A - Z)");
    }

    public void sortZA() {
        page.getByLabel("Sort").selectOption("Name (Z - A)");
    }
}
