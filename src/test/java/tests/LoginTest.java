package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.LoginPage;

@UsePlaywright(BaseTest.class)
public class LoginTest {

    private LoginPage loginPage;
    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com/auth/login");
        loginPage = new LoginPage(page);
    }

    @Test
    @Tag("smoke")
    void shouldShowNoEmailAndPasswordValidationError() {
        loginPage.clickSubmitBtn();
        loginPage.emailError();
        loginPage.passwordError();
    }

    @Test
    void shouldShowEmailValidationError() {
        loginPage.passwordInput("topSecret");
        loginPage.clickSubmitBtn();
        loginPage.emailError();
    }

    @Test
    void shouldShowPasswordValidationError() {
        loginPage.emailInput("a@b.c");
        loginPage.clickSubmitBtn();
        loginPage.passwordError();
    }

    @Test
    void shouldShowWrongEmailAndPasswordValidationError() {
        loginPage.emailInput("a@b.c");
        loginPage.passwordInput("topSecret");
        loginPage.clickSubmitBtn();
        loginPage.loginError();
    }

    @Test
    void shouldShowPasswordTest(Page page) {
        page.locator("#password").fill("topSecret");
        page.locator(".input-group-append").click();

        String passwordText = page.locator("#password").inputValue();
        Assertions.assertEquals("topSecret", passwordText, "Text content does not match!");
    }

    @Test
    void shouldOpenSignUpTest() {
        loginPage.clickRegisterLink();
        String actualText = loginPage.h3Text();

        Assertions.assertEquals("Customer registration", actualText, "Text content does not match!");
    }

    @Test
    void shouldOpenForgotPasswordTest(Page page) {
        loginPage.clickForgotPasswordLink();
        String actualText = loginPage.h3Text();

        Assertions.assertEquals("Forgot Password", actualText, "Text content does not match!");
    }
}
