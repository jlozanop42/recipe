package jslozano.recipe.services;

import jslozano.recipe.commands.RecipeCommand;
import jslozano.recipe.converters.RecipeCommandToRecipe;
import jslozano.recipe.converters.RecipeToRecipeCommand;
import jslozano.recipe.model.Recipe;
import jslozano.recipe.repositories.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository,
                             RecipeCommandToRecipe recipeCommandToRecipe,
                             RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipeSet = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipe -> recipeSet.add(recipe));
        return recipeSet;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(id);
        if(!recipeOptional.isPresent()){
            throw new RuntimeException("Recipe Not Found!");
        }
        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);
        // It's now a POJO, it's not a Hibernate Object. We can persist it in the database. The command object
        // it's only for encapsulating the data and it's a bridge with the web client (DTO)

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        // savedRecipe has the id property.
        return recipeToRecipeCommand.convert(savedRecipe);
    }
// The transactional it's need because we are doing a conversion outside the spring context
    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommand.convert(findById(id));
        /*
        Goes to the database and bring back the recipe by its id. Convert it to the command recipe.
        The repositories are managed by spring Data Jpa
         */
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
