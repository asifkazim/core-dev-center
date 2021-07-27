package az.core.service.impl;

import az.core.error.ContactNotFoundException;
import az.core.mapper.ContactMapper;
import az.core.model.dto.ContactDto;
import az.core.model.entity.Contact;
import az.core.repository.ContactRepository;
import az.core.service.ContactService;
import az.core.util.ApplicationCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public List<ContactDto> getAllContacts() {
        log.info("Request, GET All Contacts");
        List<Contact> contacts = contactRepository.findAll();
        log.info("Response, contacts:{}", contacts);
        return contactMapper.entitiesToDto(contacts);
    }

    @Override
    public ContactDto getById(Long id) {
        log.info("Request, GET Contact By Id, id:{}", id);
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException(ApplicationCode.CONTACT_NOT_FOUND, "Contact not found!")
        );
        log.info("Response, Contact By Id, id:{}, contact:{} ", id, contact);
        return contactMapper.entityToDto(contact);
    }

    @Override
    public ContactDto addContact(ContactDto contactDto) {
        log.info("Request, ADD Contact, contact:{}", contactDto);
        Contact contact = contactMapper.dtoToEntity(contactDto);
        contactRepository.save(contact);
        log.info("Response, ADDED Contact, contact:{}", contact);
        return contactDto;
    }

    @Override
    public ContactDto updateContact(Long id, ContactDto contactDto) {
        log.info("Request, UPDATE Contact, id:{}, contact:{}", id, contactDto);
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> new ContactNotFoundException(ApplicationCode.CONTACT_NOT_FOUND, "Contact not found!")
        );

        if (contact != null) {
            contact = contactMapper.dtoToEntity(contactDto);
            contact.setId(id);
        }
        contactRepository.save(contact);
        log.info("Response, UPDATED Contact, id:{}, contact:{}", id, contact);
        return contactDto;
    }

    @Override
    public void deleteContact(Long id) {
        log.info("Request, DELETE Contact, id:{}", id);
        contactRepository.deleteById(id);
        log.info("Response, DELETED Contact");

    }
}
