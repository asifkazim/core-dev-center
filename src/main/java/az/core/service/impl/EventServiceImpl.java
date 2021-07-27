package az.core.service.impl;

import az.core.error.EventNotFoundException;
import az.core.mapper.EventMapper;
import az.core.model.dto.EventDto;
import az.core.model.entity.Event;
import az.core.repository.EventRepository;
import az.core.service.EventService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventDto> getAllEvents() {
        log.info("Request, GET All Events");
        List<Event> events = eventRepository.findAll();
        log.info("Response, events:{}", events);
        return eventMapper.entitiesToDto(events);
    }

    @Override
    public EventDto getById(Long id) {
        log.info("Request, GET Event By Id, id:{}", id);
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new EventNotFoundException(ApplicationCode.EVENT_NOT_FOUND, "Event not found!")
        );
        log.info("Response, Event By Id, id:{}, event:{} ", id, event);
        return eventMapper.entityToDto(event);
    }

    @Override
    public EventDto addEvent(EventDto eventDto) {
        log.info("Request, ADD Event, event:{}", eventDto);
        Event event = eventMapper.dtoToEntity(eventDto);
        eventRepository.save(event);
        log.info("Response, ADDED Event, event:{}", event);
        return eventDto;
    }

    @Override
    public EventDto updateEvent(Long id, EventDto eventDto) {
        log.info("Request, UPDATE Event, id:{}, event:{}", id, eventDto);
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new EventNotFoundException(ApplicationCode.EVENT_NOT_FOUND, "Event not found!")
        );

        if (event != null) {
            event = eventMapper.dtoToEntity(eventDto);
            event.setId(id);
        }
        eventRepository.save(event);
        log.info("Response, UPDATED Event, id:{}, event:{}", id, event);
        return eventDto;
    }

    @Override
    public void deleteEvent(Long id) {
        log.info("Request, DELETE Event, id:{}", id);
        eventRepository.deleteById(id);
        log.info("Response, DELETED Event");
    }
}
