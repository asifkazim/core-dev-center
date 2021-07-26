package az.core.service;

import az.core.model.dto.EventDto;

import java.util.List;

public interface EventService {
    List<EventDto> getAllEvents();

    EventDto getById(Long id);

    EventDto addEvent(EventDto eventDto);

    EventDto updateEvent(Long id, EventDto eventDto);

    void deleteEvent(Long id);
}
