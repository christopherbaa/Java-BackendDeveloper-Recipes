package de.nutposit.javabackenddeveloper_recipes.dto;

import de.nutposit.javabackenddeveloper_recipes.model.Recipe;

import java.time.LocalDateTime;
import java.util.List;


public class RecipeDto {
    private String name;

    private LocalDateTime date;
    private String category;
    private String description;
    private List<String> ingredients;
    private List<String> directions;


    public RecipeDto(Recipe recipe) {
        this.name = recipe.getName();
        this.date = recipe.getDate();
        this.category = recipe.getCategory();
        this.description = recipe.getDescription();
        this.ingredients = recipe.getIngredients();
        this.directions = recipe.getDirections();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getDirections() {
        return directions;
    }

    public void setDirections(List<String> directions) {
        this.directions = directions;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
