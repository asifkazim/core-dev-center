package az.core.repository;

import az.core.model.entity.BlogCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogCategoryRepository extends JpaRepository<BlogCategory, Long> {

    BlogCategory findByName(String categoryName);
}
