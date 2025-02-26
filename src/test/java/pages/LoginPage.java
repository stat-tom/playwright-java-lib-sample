package pages;

import com.microsoft.playwright.Page;

public class LoginPage extends BasePage {

    public LoginPage(Page page) {
        super(page);
    }

    public void login(String email, String password) {
        page.locator("#email").fill(email);
        page.locator("#password").fill(password);
        page.locator(".btnSubmit").click();
    }

    public boolean isValidationErrorVisible(String fieldId) {
        return page.locator("#" + fieldId + "-error").isVisible();
    }

    public void clickRegisterLink() {
        page.getByTestId("register-link").click();
    }

    public void clickForgotPasswordLink() {
        page.getByTestId("forgot-password-link").click();
    }
}
