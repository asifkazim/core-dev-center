package az.core.mapper;

import az.core.model.dto.request.BlogCategoryRequestDto;
import az.core.model.dto.response.BlogCategoryResponseDto;
import az.core.model.entity.BlogCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface BlogCategoryMapper {

    List<BlogCategoryResponseDto> entitiesToDto(List<BlogCategory> blogCategory);

    BlogCategoryResponseDto entityToDto(BlogCategory blogCategory);

    BlogCategory dtoToEntity(BlogCategoryRequestDto categoryRequestDto);

}
