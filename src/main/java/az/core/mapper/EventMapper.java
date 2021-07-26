package az.core.mapper;

import az.core.model.dto.ContactDto;
import az.core.model.dto.EventDto;
import az.core.model.entity.Contact;
import az.core.model.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface EventMapper {

    List<EventDto> entitiesToDto(List<Event> events);

    EventDto entityToDto(Event event);

    Event dtoToEntity(EventDto eventDto);
}
