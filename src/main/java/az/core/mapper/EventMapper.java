package az.core.mapper;

import az.core.model.dto.request.EventRequestDto;
import az.core.model.dto.response.BlogResponseDto;
import az.core.model.dto.response.EventResponseDto;
import az.core.model.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    default List<EventResponseDto> entitiesToDto(List<Event> events){
        List<EventResponseDto> dtos = new ArrayList<>();
        events.forEach(event -> dtos.add(entityToDto(event)));
        return dtos;
    }

    EventResponseDto entityToDto(Event event);

    Event dtoToEntity(EventRequestDto eventRequestDto);
}
