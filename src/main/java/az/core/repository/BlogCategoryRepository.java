package az.core.repository;

import az.core.model.entity.BlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {

    Optional<BlogCategory> findByName(String categoryName);
}
