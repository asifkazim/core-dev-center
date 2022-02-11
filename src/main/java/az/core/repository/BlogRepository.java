package az.core.repository;

import az.core.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog, Long> {


    List<Blog> findAllByBlogCategory(Long id);

    Optional<Blog> findByUrl(String url);
}

