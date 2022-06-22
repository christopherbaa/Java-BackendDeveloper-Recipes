package de.nutposit.javabackenddeveloper_recipes.service;

import de.nutposit.javabackenddeveloper_recipes.dto.RecipeDto;
import de.nutposit.javabackenddeveloper_recipes.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class RecipeServiceImpl implements RecipeService{

    private final RecipeRepository recipes;

    public RecipeServiceImpl(@Autowired RecipeRepository recipes) {
        this.recipes = recipes;
    }

    public RecipeRepository getRecipes() {
        return recipes;
    }

    @Override
    public ArrayList<RecipeDto> getRecipesByName(String name) {
        ArrayList<RecipeDto> recipes = new ArrayList<>();
        this.recipes.findAllByNameContainingIgnoreCaseOrderByDateDesc(name).forEach(r -> recipes.add(new RecipeDto(r)));
        return recipes;
    }

    @Override
    public ArrayList<RecipeDto> getRecipesByCategory(String category) {
        ArrayList<RecipeDto> recipes = new ArrayList<>();
        this.recipes.findAllByCategoryIgnoreCaseOrderByDateDesc(category).forEach(r -> recipes.add(new RecipeDto(r)));
        return recipes;
    }

}
