package az.core.service;

import az.core.model.entity.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> getAllContact();

    Contact getById(Long id);

    Contact addContact(Contact contact);

    Contact updateContact(Long id,Contact contact);

    void deleteContact(Long id);
}
