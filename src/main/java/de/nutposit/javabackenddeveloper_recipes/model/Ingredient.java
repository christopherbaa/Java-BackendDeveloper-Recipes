package de.nutposit.javabackenddeveloper_recipes.model;

import javax.persistence.Embeddable;

@Embeddable
public class Ingredient {

    private String ingredient;

    public Ingredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public Ingredient() {
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
