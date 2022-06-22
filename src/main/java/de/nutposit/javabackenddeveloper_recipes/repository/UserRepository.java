package de.nutposit.javabackenddeveloper_recipes.repository;

import de.nutposit.javabackenddeveloper_recipes.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmail(String email);
}
