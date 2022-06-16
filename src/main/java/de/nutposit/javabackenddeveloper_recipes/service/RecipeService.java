package de.nutposit.javabackenddeveloper_recipes.service;

import de.nutposit.javabackenddeveloper_recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService{

    private final RecipeRepository recipes;

    public RecipeService(@Autowired RecipeRepository recipes) {
        this.recipes = recipes;
    }

    public RecipeRepository getRecipes() {
        return recipes;
    }

}
