package pages;

import com.microsoft.playwright.Page;

import java.nio.file.Path;

public class ContactPage extends BasePage {

    public ContactPage(Page page) {
        super(page);
    }

    public void submitForm() {
        page.locator(".btnSubmit").click();
    }

    public void fillForm(String firstName, String lastName, String email, String subject, String message, Path fileToUpload) {
        page.getByLabel("First name").fill(firstName);
        page.getByLabel("Last name").fill(lastName);
        page.getByLabel("Email address").fill(email);
        page.getByLabel("Subject").selectOption(subject);
        page.getByLabel("Message").fill(message);
        page.setInputFiles("#attachment", fileToUpload);
    }

    public boolean isValidationErrorVisible(String fieldId) {
        return page.locator("#" + fieldId + "_alert").isVisible();
    }
}
