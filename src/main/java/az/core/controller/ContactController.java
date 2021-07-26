package az.core.controller;

import az.core.model.dto.ContactDto;
import az.core.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactDto>> getAllContacts() {
        List<ContactDto> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactDto> getById(@PathVariable Long id) {
        ContactDto contact = contactService.getById(id);
        return ResponseEntity.ok(contact);    }

    @PostMapping
    public ResponseEntity<ContactDto> addContact(@RequestBody ContactDto contactDto) {
        ContactDto contact = contactService.addContact(contactDto);
        return ResponseEntity.ok(contact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDto> updateContact(@PathVariable Long id, @RequestBody ContactDto contactDto) {
        ContactDto contact = contactService.updateContact(id, contactDto);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("/{id}")
    public void deleteContact(@PathVariable Long id) {
        contactService.deleteContact(id);
    }

}
