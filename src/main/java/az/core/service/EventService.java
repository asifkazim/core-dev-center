package az.core.service;

import az.core.model.dto.request.EventRequestDto;
import az.core.model.dto.response.BlogResponseDto;
import az.core.model.dto.response.EventResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface EventService {
    List<EventResponseDto> getAllEvents();

    EventResponseDto getById(Long id);

    EventResponseDto addEvent(EventRequestDto eventRequestDto);

    EventResponseDto updateEvent(Long id, EventRequestDto eventRequestDto);

    EventResponseDto deleteEvent(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);

    EventResponseDto getByUrl(String url);
}
