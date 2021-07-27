package az.core.repository;

import az.core.model.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
    Optional<CourseCategory> findByName(String categoryName);
}
