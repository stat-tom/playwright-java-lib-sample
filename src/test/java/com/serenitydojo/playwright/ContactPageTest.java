package com.serenitydojo.playwright;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.junit.UsePlaywright;
import org.junit.jupiter.api.*;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@UsePlaywright(BaseTest.class)
public class ContactPageTest {

    @BeforeEach
    void setUp(Page page) {
        BaseTest.openPage(page, "https://practicesoftwaretesting.com/contact");
    }

    @Test
    void shouldShowAllValidationErrors(Page page) {
        page.locator(".btnSubmit").click();

        PlaywrightAssertions.assertThat(page.locator("#first_name_alert")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#last_name_alert")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#email_alert")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#subject_alert")).isVisible();
        PlaywrightAssertions.assertThat(page.locator("#message_alert")).isVisible();
    }

    @Test
    void shouldShowFirstNameText(Page page) {
        page.locator("input[placeholder='Your first name *']").fill("Mr.");
        PlaywrightAssertions.assertThat(page.locator("#first_name")).hasValue("Mr.");
    }

    @Test
    void completeForm(Page page) throws URISyntaxException {
        //Arrange
        var firstNameField = page.getByLabel("First name");
        var lastNameField = page.getByLabel("Last name");
        var emailAddressField = page.getByLabel("Email address");
        var subjectField = page.getByLabel("Subject");
        var messageField = page.getByLabel("Message");
        var uploadField = page.getByLabel("Attachment");

        //Act
        firstNameField.fill("Lorem ipsum");
        lastNameField.fill("dolor sit amet");
        emailAddressField.fill("consectetur@adipiscing.elit");
        messageField.fill("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        subjectField.selectOption("Warranty");

        Path fileToUpload = Paths.get(ClassLoader.getSystemResource("data/test-file.txt").toURI());
        page.setInputFiles("#attachment", fileToUpload);

        //Assert
        assertThat(firstNameField).hasValue("Lorem ipsum");
        assertThat(lastNameField).hasValue("dolor sit amet");
        assertThat(emailAddressField).hasValue("consectetur@adipiscing.elit");
        assertThat(messageField).hasValue("Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
        assertThat(subjectField).hasValue("warranty");

        String uploadedFile = uploadField.inputValue();
        org.assertj.core.api.Assertions.assertThat(uploadedFile).endsWith("test-file.txt");
    }
}
