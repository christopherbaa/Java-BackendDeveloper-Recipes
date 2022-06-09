package de.nutposit.javabackenddeveloper_recipes.controller;

import de.nutposit.javabackenddeveloper_recipes.model.Recipe;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private Recipe recipe;

    public RecipeController() {
        this.recipe = new Recipe();
    }

    @GetMapping
    public Recipe getRecipes() {
        return this.recipe;
    }

    @PostMapping
    public void postRecipe(@RequestBody Recipe recipe) {
        this.recipe = recipe;
    }

}
