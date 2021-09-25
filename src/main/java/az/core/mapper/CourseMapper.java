package az.core.mapper;

import az.core.model.dto.request.CourseRequestDto;
import az.core.model.dto.response.CourseResponseDto;
import az.core.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {


    default List<CourseResponseDto> entitiesToDto(List<Course> courses){
        List<CourseResponseDto> dtos = new ArrayList<>();
        courses.forEach(course -> dtos.add(entityToDto(course)));
        return dtos;
    }

    @Mapping(target = "courseCategory", source = "courseCategory.name")
    CourseResponseDto entityToDto(Course course);

    @Mapping(target = "courseCategory", ignore = true)
    Course dtoToEntity(CourseRequestDto courseRequestDto);
}
