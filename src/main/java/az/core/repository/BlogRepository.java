package az.core.repository;

import az.core.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {


    List<Blog> findAllByBlogCategory(Long id);
}
