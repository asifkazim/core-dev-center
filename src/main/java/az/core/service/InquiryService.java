package az.core.service;

import az.core.model.dto.request.InquiryRequestDto;
import az.core.model.dto.response.InquiryResponseDto;

import java.util.List;

public interface InquiryService {
    List<InquiryResponseDto> getAllInquiries();

    InquiryResponseDto getById(Long id);

    InquiryResponseDto updateInquiry(Long id, InquiryRequestDto inquiryRequestDto);

    InquiryResponseDto deleteInquiry(Long id);

    InquiryResponseDto addInquiry(InquiryRequestDto inquiryRequestDto);
}
