package az.core.mapper;

import az.core.model.dto.request.ContactRequestDto;
import az.core.model.dto.response.ContactResponseDto;
import az.core.model.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    List<ContactResponseDto> entitiesToDto(List<Contact> contacts);

    ContactResponseDto entityToDto(Contact contact);

    Contact dtoToEntity(ContactRequestDto contactRequestDto);
}
