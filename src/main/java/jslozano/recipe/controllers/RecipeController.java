package jslozano.recipe.controllers;

import jslozano.recipe.commands.RecipeCommand;
import jslozano.recipe.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable Long id, Model model){

        model.addAttribute("recipe", recipeService.findById(id));

        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("/recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe", new RecipeCommand());

        return "recipe/recipeform";
    }

    @PostMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommand command){
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);

        return "redirect:/recipe/" + savedCommand.getId() + "/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(id));

        return "recipe/recipeform";

    }
    /*
    ModelAttribute it's an anotation to say Spring to bind (amarrar) the form post parameters to the recipe
    command (DTO) object. This is going to happen for us automatically for the naming convention of the
    properties we did in the post form (the html with *).
    Redirect it´s to tell spring MVC to redirect to a specific url
     */
    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteById(@PathVariable Long id){
        log.debug("Deleting by id: " + id);

        recipeService.deleteById(id);

        return "redirect:/";

    }
}

