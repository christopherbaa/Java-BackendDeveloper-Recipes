package de.nutposit.javabackenddeveloper_recipes.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String description;
   @ElementCollection
   @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
   @Column(name = "ingredients")
    private List<String> ingredients = new ArrayList<>();
   @ElementCollection
   @CollectionTable(name = "recipe_directions", joinColumns = @JoinColumn(name = "recipe_id"))
   @Column(name = "directions")
    private List<String> directions = new ArrayList<>();

    public Recipe() {
    }

    public Recipe(long id, String name, String description, List<String> ingredients, List<String> directions) {
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

}

