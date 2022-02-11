package az.core.service;

import az.core.model.dto.request.ContactRequestDto;
import az.core.model.dto.response.ContactResponseDto;

import java.util.List;

public interface ContactService {
    
    List<ContactResponseDto> getAllContacts();

    ContactResponseDto getById(Long id);

    ContactResponseDto updateContact(Long id, ContactRequestDto categoryRequestDto);

    ContactResponseDto deleteContact(Long id);

    ContactResponseDto addContact(ContactRequestDto categoryRequestDto);

    ContactResponseDto getByUrl(String url);
}
