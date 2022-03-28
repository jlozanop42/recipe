package jslozano.recipe.services;

import jslozano.recipe.commands.RecipeCommand;
import jslozano.recipe.model.Recipe;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
    Recipe findById(Long id);
    RecipeCommand findCommandById(Long id);
    RecipeCommand saveRecipeCommand(RecipeCommand command);
    void deleteById(Long id);
}
