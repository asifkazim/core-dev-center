package az.core.mapper;

import az.core.model.dto.request.BlogRequestDto;
import az.core.model.dto.response.BlogResponseDto;
import az.core.model.entity.Blog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlogMapper {

    default List<BlogResponseDto> entitiesToDto(List<Blog> blogs) {
        List<BlogResponseDto> dtos = new ArrayList<>();
        blogs.forEach(blog -> dtos.add(entityToDto(blog)));
        return dtos;
    }

    @Mapping(target = "blogCategory", source = "blogCategory.name")
    BlogResponseDto entityToDto(Blog blog);


    @Mapping(target = "blogCategory", ignore = true)
    Blog dtoToEntity(BlogRequestDto blogRequestDto);

}
