package az.core.repository;

import az.core.model.entity.CourseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseCategoryRepository extends JpaRepository<CourseCategory, Long> {
    CourseCategory findByName(String categoryName);
}
