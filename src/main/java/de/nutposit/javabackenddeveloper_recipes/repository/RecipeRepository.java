package de.nutposit.javabackenddeveloper_recipes.repository;

import de.nutposit.javabackenddeveloper_recipes.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
