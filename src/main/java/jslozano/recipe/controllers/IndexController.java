package jslozano.recipe.controllers;

import jslozano.recipe.model.Category;
import jslozano.recipe.model.UnitOfMeasure;
import jslozano.recipe.repositories.CategoryRepository;
import jslozano.recipe.repositories.UnitOfMeasureRepository;
import jslozano.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/"} )
    public String getIndexPage(Model model){

        model.addAttribute("recipes", recipeService.getRecipes());

        return "index";
    }
}
