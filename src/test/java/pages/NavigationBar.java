package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class NavigationBar extends BasePage {

    public NavigationBar(Page page) {
        super(page);
    }

    public void clickOnCategory(String category) {
        page.getByRole(AriaRole.MENUBAR, new Page.GetByRoleOptions().setName("Main Menu"))
                .getByText("Categories")
                .click();
        page.locator(".dropdown-menu").getByText(category).click();
    }


}
