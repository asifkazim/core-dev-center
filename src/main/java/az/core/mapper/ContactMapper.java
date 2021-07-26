package az.core.mapper;

import az.core.model.dto.ContactDto;
import az.core.model.entity.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ContactMapper {

    List<ContactDto> entitiesToDto(List<Contact> contacts);

    ContactDto entityToDto(Contact contact);

    Contact dtoToEntity(ContactDto contactDto);
}
