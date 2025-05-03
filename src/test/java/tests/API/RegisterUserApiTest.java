package tests.API;

import com.google.gson.Gson;
import com.microsoft.playwright.*;
import com.microsoft.playwright.junit.UsePlaywright;
import com.microsoft.playwright.options.RequestOptions;
import data.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

@UsePlaywright
public class RegisterUserApiTest {

    protected static Playwright playwright;
    private APIRequestContext requestContext;
    private final Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        playwright = Playwright.create();
        requestContext = playwright.request().newContext(
                new APIRequest.NewContextOptions()
                        .setBaseURL("https://api.practicesoftwaretesting.com")
        );
    }

    @AfterEach
    void tearDown() {
        if (requestContext != null) {
            requestContext.dispose();
        }
    }

    @Test
    void should_register_user() {
        User validUser = User.randomUser();

        String requestBody = gson.toJson(validUser);
        System.out.println("Request Body: " + requestBody);

        var response = requestContext.post("/users/register",
                RequestOptions.create()
                    .setHeader("Content-Type", "application/json")
                    .setData(validUser)
        );

        System.out.println("Response URL: " + response.url());
        System.out.println("Response Status: " + response.status());
        System.out.println("Response Body: " + response.text());

        assertThat(response.status()).isEqualTo(201);

        String responseBody = response.text();
        User createdUser = gson.fromJson(responseBody, User.class);

        assertSoftly(softly -> {
            softly.assertThat(createdUser.first_name()).isEqualTo(validUser.first_name());
            softly.assertThat(createdUser.last_name()).isEqualTo(validUser.last_name());
            softly.assertThat(createdUser.address()).isEqualTo(validUser.address());
            softly.assertThat(createdUser.phone()).isEqualTo(validUser.phone());
            softly.assertThat(createdUser.dob()).isEqualTo(validUser.dob());
            softly.assertThat(createdUser.email()).isEqualTo(validUser.email());
        });
    }
}
