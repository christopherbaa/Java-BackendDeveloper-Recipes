package de.nutposit.javabackenddeveloper_recipes.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue
    private long id;
    private LocalDateTime date;
    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "category is required")
    private String category;
    @NotBlank(message = "description is required")
    private String description;
   @ElementCollection
   @Size(min = 1, message = "At least 1 ingredient is required")
   @CollectionTable(name = "recipe_ingredients", joinColumns = @JoinColumn(name = "recipe_id"))
   @Column(name = "ingredients")
    private List<String> ingredients = new ArrayList<>();
   @ElementCollection
   @Size(min = 1, message = "At least 1 direction is required")
   @CollectionTable(name = "recipe_directions", joinColumns = @JoinColumn(name = "recipe_id"))
   @Column(name = "directions")
    private List<String> directions = new ArrayList<>();

    public Recipe() {
        this.date = LocalDateTime.now();
    }

    public Recipe(long id, String name, String category, String description, List<String> ingredients, List<String> directions) {
        this.id = id;
        this.date = LocalDateTime.now();
        this.name = name;
        this.category = category;
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

