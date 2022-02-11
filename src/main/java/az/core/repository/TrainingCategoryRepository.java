package az.core.repository;

import az.core.model.entity.TrainingCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainingCategoryRepository extends JpaRepository<TrainingCategory, Long> {
    Optional<TrainingCategory> findByName(String categoryName);
}
