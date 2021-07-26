package az.core.service;

import az.core.model.entity.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvent();

    Event getById(Long id);

    Event addEvent(Event event);

    Event updateEvent(Long id,Event event);

    void deleteEvent(Long id);
}
