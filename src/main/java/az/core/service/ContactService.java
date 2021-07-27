package az.core.service;

import az.core.model.dto.ContactDto;

import java.util.List;

public interface ContactService {
    List<ContactDto> getAllContacts();

    ContactDto getById(Long id);

    ContactDto addContact(ContactDto contactDto);

    ContactDto updateContact(Long id, ContactDto contactDto);

    void deleteContact(Long id);
}
