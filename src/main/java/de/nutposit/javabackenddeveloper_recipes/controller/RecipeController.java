package de.nutposit.javabackenddeveloper_recipes.controller;

import de.nutposit.javabackenddeveloper_recipes.dto.RecipeDto;
import de.nutposit.javabackenddeveloper_recipes.model.Recipe;
import de.nutposit.javabackenddeveloper_recipes.dto.RecipeIdDto;
import de.nutposit.javabackenddeveloper_recipes.model.User;
import de.nutposit.javabackenddeveloper_recipes.service.RecipeServiceImpl;
import de.nutposit.javabackenddeveloper_recipes.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class RecipeController {

    private final RecipeServiceImpl recipeServiceImpl;
    private final UserServiceImpl userServiceImpl;

    public RecipeController(RecipeServiceImpl recipeServiceImpl, UserServiceImpl userServiceImpl) {
        this.recipeServiceImpl = recipeServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/recipe")
    public Iterable<Recipe> getRecipes() {
        return recipeServiceImpl.getRecipes().findAll();
    }

    @GetMapping("/recipe/{id}")
    public ResponseEntity<RecipeDto> getRecipe(@PathVariable("id") Long id) {
        return this.recipeServiceImpl.getRecipes().existsById(id) ?
                new ResponseEntity<>(new RecipeDto(this.recipeServiceImpl.getRecipes().findById(id).get()), HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/recipe/search")
    public ResponseEntity<ArrayList<RecipeDto>> getRecipesByNameOrCategory(@RequestParam Map<String, String> params) {
        return params.containsKey("name") && params.size() == 1 ?
                new ResponseEntity<>(this.recipeServiceImpl.getRecipesByName(params.get("name")), HttpStatus.OK) :
                params.containsKey("category") && params.size() == 1 ?
                        new ResponseEntity<>(this.recipeServiceImpl.getRecipesByCategory(params.get("category")), HttpStatus.OK) :
                        new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/recipe/new")
    public ResponseEntity<RecipeIdDto> postRecipe(@Valid @RequestBody Recipe recipe, Principal user) {
        recipe.setUser(this.userServiceImpl.getUserRepository().findByEmail(user.getName()));
        return new ResponseEntity<>(new RecipeIdDto(this.recipeServiceImpl.getRecipes().save(recipe).getId()),
                HttpStatus.OK);
    }

    @PutMapping("/recipe/{id}")
    public ResponseEntity<String> putRecipe(@PathVariable("id") Long id, @Valid @RequestBody Recipe recipe, Principal user) {
        User actualUser = this.userServiceImpl.getUserRepository().findByEmail(user.getName());
        if(this.recipeServiceImpl.getRecipes().existsById(id)) {
            if(this.recipeServiceImpl.getRecipes().findById(id).get().getUser().getId().equals(actualUser.getId())) {
                recipe.setId(id);
                recipe.setUser(actualUser);
                this.recipeServiceImpl.getRecipes().save(recipe);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/recipe/{id}")
    public ResponseEntity<String> deleteRecipe(@PathVariable("id") Long id, Principal user) {
        Long userId = this.userServiceImpl.getUserRepository().findByEmail(user.getName()).getId();
        if(this.recipeServiceImpl.getRecipes().existsById(id)) {
            if(this.recipeServiceImpl.getRecipes().findById(id).get().getUser().getId().equals(userId)) {
                this.recipeServiceImpl.getRecipes().deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user) {
        return this.userServiceImpl.register(user) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
