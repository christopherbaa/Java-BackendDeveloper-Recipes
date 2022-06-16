package de.nutposit.javabackenddeveloper_recipes.controller;

import de.nutposit.javabackenddeveloper_recipes.model.RecipeIdentifier;
import de.nutposit.javabackenddeveloper_recipes.model.Recipe;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    // TODO Should be changed to Map to avoid double entry's
    private ArrayList<Recipe> recipes;

    public RecipeController() {
        this.recipes = new ArrayList<>();
    }

    @GetMapping
    public ArrayList<Recipe> getRecipes() {
        return this.recipes;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recipe> getRecipe(@PathVariable("id") int id) {
        return id < this.recipes.size() && id >= 0 ?
                new ResponseEntity<>(this.recipes.get(id), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/new")
    public ResponseEntity<RecipeIdentifier> postRecipe(@RequestBody Recipe recipe) {
        return this.recipes.add(recipe) ?
                new ResponseEntity<>(new RecipeIdentifier(this.recipes.indexOf(recipe)), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
