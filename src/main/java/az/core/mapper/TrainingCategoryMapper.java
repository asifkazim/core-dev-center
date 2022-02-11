package az.core.mapper;

import az.core.model.dto.request.TrainingCategoryRequestDto;
import az.core.model.dto.response.TrainingCategoryResponseDto;
import az.core.model.entity.TrainingCategory;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TrainingCategoryMapper {

    List<TrainingCategoryResponseDto> entitiesToDto(List<TrainingCategory> trainingCategories);

    TrainingCategoryResponseDto entityToDto(TrainingCategory trainingCategory);

    TrainingCategory dtoToEntity(TrainingCategoryRequestDto  trainingCategoryRequestDto);

}
