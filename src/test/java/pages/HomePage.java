package pages;

import com.microsoft.playwright.Page;

import java.util.List;

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

    public String getPageTitle() {
        return page.title();
    }

    public void sortAtoZ() {
        page.getByLabel("Sort").selectOption("Name (A - Z)");
    }

    public void sortZtoA() {
        page.getByLabel("Sort").selectOption("Name (Z - A)");
    }

    public void sortHighToLow() {
        page.getByTestId("sort").selectOption("Price (High - Low)");
    }

    public void sortLowToHigh() {
        page.getByTestId("sort").selectOption("Price (Low - High)");
    }

    public void selectProduct(String productName) {
        page.getByText(productName).click();
    }

    public List<String> getProductNames() {
        page.waitForSelector(".card");
//        page.waitForSelector(".card", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2000));
        return page.getByTestId("product-name").allInnerTexts();
    }

    public List<String> getProductPrices() {
        page.waitForSelector(".card");
//        page.waitForSelector(".card", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2000));
        return page.getByTestId("product-price").allInnerTexts();
    }
}
