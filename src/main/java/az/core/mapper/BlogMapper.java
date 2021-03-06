package az.core.mapper;

import az.core.model.dto.BlogDto;
import az.core.model.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlogMapper {

    List<BlogDto> entitiesToDto(List<Blog> blog);

    BlogDto entityToDto(Blog blog);

    Blog dtoToEntity(BlogDto blogDto);
}
