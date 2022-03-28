package jslozano.recipe.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {
    Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
    }
    @Test
    public void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals(idValue, category.getId());
        // id value it's what I'm expecting and the second parameter is the actual value
    }
    @Test
    public void getDescription() {
        String descriptionValue = "mexicanCategory";
        category.setDescription(descriptionValue);

        assertEquals(descriptionValue, category.getDescription());
    }

    @Test
    public void getRecipes() {
    }
}
