package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.error.FileCantUploadException;
import az.core.mapper.EventMapper;
import az.core.model.dto.request.EventRequestDto;
import az.core.model.dto.response.EventResponseDto;
import az.core.model.entity.Blog;
import az.core.model.entity.Event;
import az.core.repository.EventRepository;
import az.core.service.EventService;
import az.core.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final FilesService filesService;


    @Value("${minio.image-folder}")
    private String imageFolder;


    @Override
    public List<EventResponseDto> getAllEvents() {
        log.info("getAllEvents requesting...");
        List<Event> events = eventRepository.findAll();
        log.info("getAllEvents Response started with: {}", kv("events", events));
        return eventMapper.entitiesToDto(events);
    }

    @Override
    public EventResponseDto getById(Long id) {
        log.info("getById Event started with: {}", kv("id", id));
        Event event = eventRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Event.class, id);
        });
        EventResponseDto response = eventMapper.entityToDto(event);
        log.info("getById Event completed successfully with: {}", kv("id", id));
        return response;
    }

    @Override
    public EventResponseDto addEvent(EventRequestDto eventRequestDto) {
        log.info("create Event started with:{}", eventRequestDto);
        Event event = eventMapper.dtoToEntity(eventRequestDto);
        eventRepository.save(event);
        EventResponseDto eventResponseDto = eventMapper.entityToDto(event);
        log.info("create Event completed successfully with: {}", kv("response", eventResponseDto));
        return eventResponseDto;
    }

    @Override
    public EventResponseDto updateEvent(Long id, EventRequestDto eventRequestDto) {
        log.info("update Event started with: {}, {}", kv("id", id),
                kv("eventRequestDto", eventRequestDto));
        Event event = eventRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Event.class, id);
        });

        if (event != null) {
            event = eventMapper.dtoToEntity(eventRequestDto);
            event.setId(id);
        }
        eventRepository.save(event);
        EventResponseDto responseDto = eventMapper.entityToDto(event);
        log.info("update Event completed successfully with: {}, {}", kv("id", id),
                kv("response", responseDto));
        return responseDto;

    }

    @Override
    public EventResponseDto deleteEvent(Long id) {
        log.info("delete Event started with: {}", kv("id", id));

        Event event = eventRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(Event.class, id);
                }
        );
        if (event.getImage() != null) {
            deleteFile(event.getImage(), imageFolder);
        }
        eventRepository.delete(event);
        EventResponseDto eventResponseDto = eventMapper.entityToDto(event);
        log.info("delete Event completed successfully with: {}", kv("id", id));
        return eventResponseDto;
    }

    @Override
    public String uploadImage(MultipartFile file, Long id) {
        log.info("uploadImage to Event started with, partnerId: {}", id);
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Event.class, id));
        if (event.getImage() == null) {
            String fileName = filesService.uploadImage(file, imageFolder);
            event.setImage(fileName);
            eventRepository.save(event);
            log.info("uploadImage to Event completed successfully with partnerId:{}", id);
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }


    @Override
    public String updateImage(MultipartFile file, Long id) {
        log.info("updateImage to Event started with, {}",
                kv("partnerId", id));
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Event.class, id));
        deleteFile(event.getImage(), imageFolder);
        String fileName = filesService.uploadImage(file, imageFolder);
        event.setImage(fileName);
        eventRepository.save(event);
        log.info("updateImage to Event completed successfully with {}",
                kv("partnerId", event));
        return fileName;
    }

    @Override
    public void deleteUserImage(Long id) {
        log.info("deleteUserImage started from Event with {}", kv("id", id));
        Event event = eventRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Blog.class, id));
        if (event.getImage() != null) {
            filesService.deleteFile(event.getImage(), imageFolder);
            event.setImage(null);
            eventRepository.save(event);
        }
        log.info("deleteUserImage completed successfully from Event with {} ", kv("id", id));
    }

    @Override
    public void deleteFile(String fileName, String folder) {

    }

    @Override
    public byte[] getFile(String fileName, String folder) {
        log.info("getFile started with {}", kv("fileName", fileName));
        return filesService.getFile(fileName, folder);
    }
}
