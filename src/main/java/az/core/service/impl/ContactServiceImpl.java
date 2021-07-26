package az.core.service.impl;

import az.core.mapper.ContactMapper;
import az.core.model.dto.ContactDto;
import az.core.model.entity.Contact;
import az.core.repository.ContactRepository;
import az.core.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public List<ContactDto> getAllContacts() {
        List<Contact> contacts = contactRepository.findAll();
        return contactMapper.entitiesToDto(contacts);
    }

    @Override
    public ContactDto getById(Long id) {
        Contact contact = contactRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return contactMapper.entityToDto(contact);
    }

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        Contact contact = contactMapper.dtoToEntity(contactDto);
        contactRepository.save(contact);
        return contactDto;
    }

    @Override
    public ContactDto updateContact(Long id, ContactDto contactDto) {
        Contact contact = contactRepository.findById(id).orElseThrow(IllegalArgumentException::new);

        if (contact != null) {
            contact = contactMapper.dtoToEntity(contactDto);
            contact.setId(id);
        }
        contactRepository.save(contact);
        return contactDto;
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
