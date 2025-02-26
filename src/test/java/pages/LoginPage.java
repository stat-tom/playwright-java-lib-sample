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

    public void clickRegisterLink() {
        page.getByTestId("register-link").click();
    }

    public void clickForgotPasswordLink() {
        page.getByTestId("forgot-password-link").click();
    }

    public void emailError() {
        page.locator("#email-error").isVisible();
    }

    public void passwordError() {
        page.locator("#password-error").isVisible();
    }

    public void clickSubmitBtn() {
        page.locator(".btnSubmit").click();
    }

    public void emailInput(String email) {
        page.locator("#email").fill(email);
    }

    public void passwordInput(String password) {
        page.locator("#password").fill(password);
    }

    public void loginError() {
        page.locator("#login-error").isVisible();
    }

    public String h3Text() {
        return page.locator("h3").innerText();
    }
}
