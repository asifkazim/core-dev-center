package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.mapper.ContactMapper;
import az.core.model.dto.request.ContactRequestDto;
import az.core.model.dto.response.ContactResponseDto;
import az.core.model.entity.Contact;
import az.core.repository.ContactRepository;
import az.core.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;
    private final ContactMapper contactMapper;

    @Override
    public List<ContactResponseDto> getAllContacts() {
        log.info("getAllContact requesting...");
        List<Contact> contact = contactRepository.findAll();
        log.info("getAllContact Response started with: {}", kv("contact", contact));
        return contactMapper.entitiesToDto(contact);
    }

    @Override
    public ContactResponseDto getById(Long id) {
        log.info("getById Contact started with: {}", kv("id", id));
        Contact contact = contactRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Contact.class, id);
        });
        ContactResponseDto responseDto = contactMapper.entityToDto(contact);
        log.info("getById Contact completed successfully with: {}", kv("id", id));
        return responseDto;

    }

    @Override
    public ContactResponseDto getByUrl(String url) {
        log.info("getByUrl Contact started with: {}", kv("url", url));
        Contact contact = contactRepository.findByUrl(url).orElseThrow(() -> {
            throw new EntityNotFoundException(Contact.class, url);
        });
        ContactResponseDto responseDto = contactMapper.entityToDto(contact);
        log.info("getByUrl Contact completed successfully with: {}", kv("url", url));
        return responseDto;
    }

    @Override
    public ContactResponseDto addContact(ContactRequestDto contactRequestDto) {
        log.info("create Contact started with:{}", contactRequestDto);
        Contact contact = contactMapper.dtoToEntity(contactRequestDto);
        contactRepository.save(contact);
        ContactResponseDto contactResponseDto = contactMapper.entityToDto(contact);
        log.info("create Contact completed successfully with: {}", kv("contact", contactResponseDto));
        return contactResponseDto;
    }


    @Override
    public ContactResponseDto updateContact(Long id, ContactRequestDto contactRequestDto) {
        log.info("update Contact started with: {}, {}", kv("id", id),
                kv("ContactRequestDto", contactRequestDto));
        Contact contact = contactRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Contact.class, id);
        });
        if (contact != null) {
            contact = contactMapper.dtoToEntity(contactRequestDto);
            contact.setId(id);
        }
        contactRepository.save(contact);
        ContactResponseDto responseDto = contactMapper.entityToDto(contact);
        log.info("update Contact completed successfully with: {}, {}", kv("id", id),
                kv("contact", responseDto));
        return responseDto;
    }

    @Override
    public ContactResponseDto deleteContact(Long id) {
        log.info("Delete Contact started with: {}", kv("id", id));
        Contact contact = contactRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(Contact.class, id);
                }
        );
        contactRepository.delete(contact);
        ContactResponseDto responseDto = contactMapper.entityToDto(contact);
        log.info("delete Contact completed successfully with: {}", kv("id", id));
        return responseDto;
    }

}
