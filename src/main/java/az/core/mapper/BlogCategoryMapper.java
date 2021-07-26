package az.core.mapper;

import az.core.model.dto.BlogCategoryDto;
import az.core.model.entity.BlogCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlogCategoryMapper {

    List<BlogCategoryDto> entitiesToDto(List<BlogCategory> blogCategory);

    BlogCategoryDto entityToDto(BlogCategory blogCategory);

    BlogCategory dtoToEntity(BlogCategoryDto blogCategoryDto);

}
