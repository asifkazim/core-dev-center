package az.core.mapper;

import az.core.model.dto.request.BlogCategoryRequestDto;
import az.core.model.dto.request.CourseCategoryRequestDto;
import az.core.model.dto.response.BlogCategoryResponseDto;
import az.core.model.dto.response.CourseCategoryResponseDto;
import az.core.model.entity.BlogCategory;
import az.core.model.entity.CourseCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CourseCategoryMapper {

    List<CourseCategoryResponseDto> entitiesToDto(List<CourseCategory> blogCategory);

    CourseCategoryResponseDto entityToDto(CourseCategory blogCategory);

    CourseCategory dtoToEntity(CourseCategoryRequestDto categoryRequestDto);

}
