package az.core.controller;

import az.core.model.dto.FileDto;
import az.core.model.dto.request.EventRequestDto;
import az.core.model.dto.response.BlogResponseDto;
import az.core.model.dto.response.EventResponseDto;
import az.core.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    @Value("${minio.image-folder}")
    private String imageFolder;

    @GetMapping
    public ResponseEntity<List<EventResponseDto>> getAllEvents() {
        List<EventResponseDto> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponseDto> getById(@PathVariable Long id) {
        EventResponseDto event = eventService.getById(id);
        return ResponseEntity.ok(event);
    }

    @GetMapping("/urls/{url}")
    public ResponseEntity<EventResponseDto> getByUrl(@PathVariable String url) {
        EventResponseDto event = eventService.getByUrl(url);
        return ResponseEntity.ok(event);
    }

    @PostMapping
    public ResponseEntity<EventResponseDto> addEvent(@RequestBody EventRequestDto eventRequestDto) {
        EventResponseDto event = eventService.addEvent(eventRequestDto);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponseDto> updateEvent(@PathVariable Long id, @RequestBody EventRequestDto eventRequestDto) {
        EventResponseDto event = eventService.updateEvent(id, eventRequestDto);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EventResponseDto> deleteEvent(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(eventService.deleteEvent(id));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @PostMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> createImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(eventService.uploadImage(file, id)));
    }


    @PutMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> updateImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(eventService.updateImage(file, id)));
    }

    @GetMapping(value = "/image/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable("fileName") String fileName) {
        return eventService.getFile(fileName, imageFolder);
    }

    @DeleteMapping("/image/{id}")
    public void deleteUserImage(@PathVariable("id") Long id) {
        eventService.deleteUserImage(id);
    }

}
