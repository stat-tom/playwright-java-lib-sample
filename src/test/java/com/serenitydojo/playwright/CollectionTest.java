package com.serenitydojo.playwright;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Optional;

public class CollectionTest extends BaseTest {

    @Test
    void shouldShowCollectionOfItems() {
        List<String> itemNames = page.getByTestId("product-name").allTextContents();
        System.out.println(itemNames);

        Assertions.assertFalse(itemNames.isEmpty(), "Expected at least 1 item in the collection");

        String actualItem = itemNames.get(2).trim();
        Assertions.assertEquals("Bolt Cutters", actualItem, "Third item should be 'Bolt Cutters'");

        Optional<String> foundItem = itemNames.stream()
                .map(String::trim)
                .filter(item -> item.equals("Thor Hammer"))
                .findFirst();
        Assertions.assertTrue(foundItem.isPresent(), "'Thor Hammer' should be present in the collection");
    }

}
