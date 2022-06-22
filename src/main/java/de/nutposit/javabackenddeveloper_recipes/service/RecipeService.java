package de.nutposit.javabackenddeveloper_recipes.service;

import de.nutposit.javabackenddeveloper_recipes.dto.RecipeDto;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface RecipeService {

    ArrayList<RecipeDto> getRecipesByName(String name);
    ArrayList<RecipeDto> getRecipesByCategory(String category);

}
