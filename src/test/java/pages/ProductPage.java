package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;

public class ProductPage extends BasePage {

    public ProductPage(Page page) {
        super(page);
    }

    public String getProductTitle() {
        return page.getByTestId("page-title").innerText();
    }

    public void clickOnCategory(String category) {
        page.getByRole(AriaRole.MENUBAR, new Page.GetByRoleOptions().setName("Main Menu"))
                .getByText("Categories")
                .click();
        page.locator(".dropdown-menu").getByText(category).click();
    }

    public List<String> getProductNames() {
        page.waitForSelector(".card");
//        page.waitForSelector(".card", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE).setTimeout(2000));
        return page.getByTestId("product-name").allInnerTexts();
    }
}
