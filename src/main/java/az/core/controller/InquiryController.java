package az.core.controller;

import az.core.model.dto.request.InquiryRequestDto;
import az.core.model.dto.response.InquiryResponseDto;
import az.core.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/inquiries")
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping
    public ResponseEntity<List<InquiryResponseDto>> getAllInquiries() {
        List<InquiryResponseDto> categories = inquiryService.getAllInquiries();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InquiryResponseDto> getById(@PathVariable Long id) {
        InquiryResponseDto categoryDto = inquiryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<InquiryResponseDto> addInquiry(@RequestBody InquiryRequestDto inquiryRequestDto) {
        InquiryResponseDto responseDto = inquiryService.addInquiry(inquiryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InquiryResponseDto> updateInquiry(@PathVariable Long id, @RequestBody InquiryRequestDto inquiryRequestDto) {
        InquiryResponseDto responseDto = inquiryService.updateInquiry(id, inquiryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<InquiryResponseDto> deleteInquiry(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(inquiryService.deleteInquiry(id));
    }
}
