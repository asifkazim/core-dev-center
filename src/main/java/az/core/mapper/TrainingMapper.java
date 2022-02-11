package az.core.mapper;

import az.core.model.dto.request.TrainingRequestDto;
import az.core.model.dto.response.TrainingResponseDto;
import az.core.model.entity.Training;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TrainingMapper {


    default List<TrainingResponseDto> entitiesToDto(List<Training> trainings) {
        List<TrainingResponseDto> dtos = new ArrayList<>();
        trainings.forEach(training -> dtos.add(entityToDto(training)));
        return dtos;
    }

    @Mapping(target = "categoryId", source = "trainingCategory.id")
    TrainingResponseDto entityToDto(Training training);

    @Mapping(target = "trainingCategory", ignore = true)
    Training dtoToEntity(TrainingRequestDto trainingRequestDto);
}
