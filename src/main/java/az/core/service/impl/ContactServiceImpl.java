package az.core.service.impl;

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

    @Override
    public List<Contact> getAllContact() {
        return contactRepository.findAll();
    }

    @Override
    public Contact getById(Long id) {
        return contactRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Contact addContact(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact updateContact(Long id, Contact contact) {
        Contact existContact = contactRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        existContact.setName(contact.getName());
        existContact.setSurname(contact.getSurname());
        existContact.setEmail(contact.getEmail());
        existContact.setHeader(contact.getHeader());
        existContact.setMessage(contact.getMessage());
        return contactRepository.save(existContact);
    }

    @Override
    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
