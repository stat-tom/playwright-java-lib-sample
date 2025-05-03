package data;

import net.datafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public record User(
        String first_name,
        String last_name,
        Map<String, String> address,
        String city,
        String state,
        String country,
        String postcode,
        String phone,
        String dob,
        String password,
        String email) {
    public static User randomUser() {
        Faker fake = new Faker();

        int year = fake.number().numberBetween(1970, 2000);
        int month = fake.number().numberBetween(1, 12);
        int day = fake.number().numberBetween(1, 28);
        LocalDate date = LocalDate.of(year, month, day);
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return new User(
                fake.name().firstName(),
                fake.name().lastName(),
                Map.of(
                        "street", fake.address().streetAddress(),
                        "city", fake.address().city(),
                        "state", fake.address().state(),
                        "country", fake.address().country(),
                        "postal_code", fake.address().postcode()
                ),
                fake.address().city(),
                fake.address().state(),
                fake.address().country(),
                fake.address().postcode(),
                fake.phoneNumber().phoneNumber(),
                formattedDate,
                fake.internet().password(8, 16, true, true, true),
                fake.internet().emailAddress()
        );
    }

    public User withPassword(String password) {
        return new User(
                first_name,
                last_name,
                address,
                city,
                state,
                country,
                postcode,
                phone,
                dob,
                password,
                email);
    }

    public User withFirstName(String first_name) {
        return new User(first_name,last_name,address,city,state,country,postcode,phone,dob,password,email);
    }

}
