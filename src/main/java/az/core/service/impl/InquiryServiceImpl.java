package az.core.service.impl;

import az.core.config.MailConfiguration;
import az.core.error.EntityNotFoundException;
import az.core.mapper.InquiryMapper;
import az.core.model.dto.request.InquiryRequestDto;
import az.core.model.dto.response.InquiryResponseDto;
import az.core.model.entity.Inquiry;
import az.core.repository.InquiryRepository;
import az.core.service.InquiryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@RequiredArgsConstructor
@Service
public class InquiryServiceImpl implements InquiryService {

    private final MailConfiguration mailConfiguration;
    private final InquiryRepository inquiryRepository;
    private final InquiryMapper inquiryMapper;

    @Override
    public List<InquiryResponseDto> getAllInquiries() {
        log.info("getAllUser requesting...");
        List<Inquiry> inquiry = inquiryRepository.findAll();
        log.info("getAllUser Response started with: {}", kv("user", inquiry));
        return inquiryMapper.entitiesToDto(inquiry);
    }

    @Override
    public InquiryResponseDto getById(Long id) {
        log.info("getById User started with: {}", kv("id", id));
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Inquiry.class, id);
        });
        InquiryResponseDto responseDto = inquiryMapper.entityToDto(inquiry);
        log.info("getById User completed successfully with: {}", kv("id", id));
        return responseDto;

    }

    @Override
    public InquiryResponseDto addInquiry(InquiryRequestDto inquiryRequestDto) {
        log.info("create User started with:{}", inquiryRequestDto);
        mailConfiguration.sendMail(inquiryRequestDto);
        Inquiry inquiry = inquiryMapper.dtoToEntity(inquiryRequestDto);
        inquiryRepository.save(inquiry);
        InquiryResponseDto inquiryResponseDto = inquiryMapper.entityToDto(inquiry);
        log.info("create User completed successfully with: {}", kv("user", inquiryResponseDto));
        return inquiryResponseDto;
    }

    @Override
    public InquiryResponseDto updateInquiry(Long id, InquiryRequestDto inquiryRequestDto) {
        log.info("update User started with: {}, {}", kv("id", id),
                kv("UserRequestDto", inquiryRequestDto));
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Inquiry.class, id);
        });
        if (inquiry != null) {
            inquiry = inquiryMapper.dtoToEntity(inquiryRequestDto);
            inquiry.setId(id);
        }
        inquiryRepository.save(inquiry);
        InquiryResponseDto responseDto = inquiryMapper.entityToDto(inquiry);
        log.info("update User completed successfully with: {}, {}", kv("id", id),
                kv("user", responseDto));
        return responseDto;
    }

    @Override
    public InquiryResponseDto deleteInquiry(Long id) {
        log.info("Delete User started with: {}", kv("id", id));
        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(Inquiry.class, id);
                }
        );
        inquiryRepository.delete(inquiry);
        InquiryResponseDto responseDto = inquiryMapper.entityToDto(inquiry);
        log.info("delete User completed successfully with: {}", kv("id", id));
        return responseDto;
    }

}



