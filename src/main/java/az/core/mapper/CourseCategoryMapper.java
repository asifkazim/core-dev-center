package az.core.mapper;

import az.core.model.dto.CourseCategoryDto;
import az.core.model.entity.CourseCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CourseCategoryMapper {

    List<CourseCategoryDto> entitiesToDto(List<CourseCategory> blogCategory);

    CourseCategoryDto entityToDto(CourseCategory courseCategory);

    CourseCategory dtoToEntity(CourseCategoryDto courseCategoryDto);

}
