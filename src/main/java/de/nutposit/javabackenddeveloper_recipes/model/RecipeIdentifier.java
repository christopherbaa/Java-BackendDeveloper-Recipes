package de.nutposit.javabackenddeveloper_recipes.model;

public class RecipeIdentifier {

    private int id;

    public RecipeIdentifier(int id) {
        this.id = id;
    }

    public RecipeIdentifier() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
