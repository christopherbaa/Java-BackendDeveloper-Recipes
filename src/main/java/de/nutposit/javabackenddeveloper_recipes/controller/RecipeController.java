package de.nutposit.javabackenddeveloper_recipes.controller;

import de.nutposit.javabackenddeveloper_recipes.dto.RecipeDto;
import de.nutposit.javabackenddeveloper_recipes.model.Recipe;
import de.nutposit.javabackenddeveloper_recipes.dto.RecipeIdDto;
import de.nutposit.javabackenddeveloper_recipes.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;


@RestController
@RequestMapping("/api/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(@Autowired RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Iterable<Recipe> getRecipes() {
        return recipeService.getRecipes().findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable("id") Long id) {
        return this.recipeService.getRecipes().existsById(id) ?
                new ResponseEntity<>(new RecipeDto(this.recipeService.getRecipes().findById(id).get()), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<ArrayList<RecipeDto>> getRecipesByNameOrCategory(@RequestParam Map<String, String> params) {
        return params.containsKey("name") && params.size() == 1 ?
                new ResponseEntity<>(this.recipeService.getRecipesByName(params.get("name")), HttpStatus.OK) :
                params.containsKey("category") && params.size() == 1 ?
                        new ResponseEntity<>(this.recipeService.getRecipesByCategory(params.get("category")), HttpStatus.OK) :
                        new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/new")
    public ResponseEntity<RecipeIdDto> postRecipe(@Valid @RequestBody Recipe recipe) {
        return new ResponseEntity<>(new RecipeIdDto(this.recipeService.getRecipes().save(recipe).getId()),
                HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putRecipe(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe) {
        if(this.recipeService.getRecipes().existsById(id)) {
            recipe.setId(id);
            this.recipeService.getRecipes().save(recipe);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id) {
        if(this.recipeService.getRecipes().existsById(id)) {
            this.recipeService.getRecipes().deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
