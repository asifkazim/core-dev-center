package az.core.service.impl;

import az.core.mapper.EventMapper;
import az.core.model.dto.EventDto;
import az.core.model.entity.Event;
import az.core.repository.EventRepository;
import az.core.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return eventMapper.entitiesToDto(events);
    }

    @Override
    public EventDto getById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return eventMapper.entityToDto(event);
    }

    @Override
    public EventDto addEvent(EventDto eventDto) {
        Event event = eventMapper.dtoToEntity(eventDto);
        eventRepository.save(event);
        return eventDto;
    }

    @Override
    public EventDto updateEvent(Long id, EventDto eventDto) {
        Event event = eventRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (event != null) {
            event = eventMapper.dtoToEntity(eventDto);
            event.setId(id);
        }
        eventRepository.save(event);
        return eventDto;

    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
