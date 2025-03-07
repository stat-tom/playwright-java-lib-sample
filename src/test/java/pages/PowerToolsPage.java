package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class PowerToolsPage extends BasePage {

    public PowerToolsPage(Page page) {
        super(page);
    }

    public void clickOnPowerToolsCategory() {
        page.getByRole(AriaRole.MENUBAR).getByText("Categories").click();
        page.getByRole(AriaRole.MENUITEM).getByText("Power Tools").click();
    }
}
