package jslozano.recipe.repositories;

import jslozano.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Optional<Category> findByDescription(String description);
    // Optional is a way to handle the null values. The object in the brackets it's what we are waiting for
}
