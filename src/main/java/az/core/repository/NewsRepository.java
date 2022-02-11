package az.core.repository;

import az.core.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    Optional<News> findByUrl(String url);
}
