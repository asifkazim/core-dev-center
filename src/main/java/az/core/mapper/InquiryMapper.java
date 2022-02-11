package az.core.mapper;

import az.core.model.dto.request.InquiryRequestDto;
import az.core.model.dto.response.InquiryResponseDto;
import az.core.model.entity.Inquiry;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface InquiryMapper {

    List<InquiryResponseDto> entitiesToDto(List<Inquiry> inquiries);

    InquiryResponseDto entityToDto(Inquiry inquiry);

    Inquiry dtoToEntity(InquiryRequestDto inquiryRequestDto);
}
