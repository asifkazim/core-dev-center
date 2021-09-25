package az.core.controller;

import az.core.model.dto.response.ContactResponseDto;
import az.core.model.dto.request.ContactRequestDto;
import az.core.service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactResponseDto>> getAllCategories() {
        List<ContactResponseDto> categories = contactService.getAllContacts();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDto> getById(@PathVariable Long id) {
        ContactResponseDto categoryDto = contactService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<ContactResponseDto> addContact(@RequestBody ContactRequestDto contactRequestDto) {
        ContactResponseDto responseDto = contactService.addContact(contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactResponseDto> updateContact(@PathVariable Long id, @RequestBody ContactRequestDto contactRequestDto) {
        ContactResponseDto responseDto = contactService.updateContact(id, contactRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ContactResponseDto> deleteContact(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(contactService.deleteContact(id));
    }

}
