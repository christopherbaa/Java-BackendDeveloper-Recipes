package de.nutposit.javabackenddeveloper_recipes.model;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private long id;

    private String name;
    private String description;
   @ElementCollection
    private ArrayList<String> ingredients;
   @ElementCollection
    private ArrayList<String> directions;

    public Recipe() {
    }

    public Recipe(long id, String name, String description, ArrayList<String> ingredients, ArrayList<String> directions) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.directions = directions;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getDirections() {
        return directions;
    }

    public void setDirections(ArrayList<String> directions) {
        this.directions = directions;
    }
}
