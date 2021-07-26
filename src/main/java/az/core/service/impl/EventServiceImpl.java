package az.core.service.impl;

import az.core.model.entity.Event;
import az.core.repository.EventRepository;
import az.core.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EventServiceImpl  implements EventService {

    private final EventRepository eventRepository;

    @Override
    public List<Event> getAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event updateEvent(Long id,Event event) {
        Event existEvent = eventRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        existEvent.setName(event.getName());
        existEvent.setStartTime(event.getStartTime());
        existEvent.setPayment(event.getPayment());
        existEvent.setPeriod(event.getPeriod());
        existEvent.setPlace(event.getPlace());
        existEvent.setDescription(event.getDescription());
        existEvent.setRemainingPeriod(event.getRemainingPeriod());
        existEvent.setModerator(event.getModerator());
        existEvent.setStatus(event.getStatus());
        existEvent.setImage(event.getImage());
        return eventRepository.save(existEvent);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
