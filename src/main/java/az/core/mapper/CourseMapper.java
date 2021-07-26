package az.core.mapper;

import az.core.model.dto.BlogDto;
import az.core.model.dto.CourseDto;
import az.core.model.entity.Blog;
import az.core.model.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CourseMapper {

    List<CourseDto> entitiesToDto(List<Course> courses);

    CourseDto entityToDto(Course course);

    Course dtoToEntity(CourseDto courseDto);
}
