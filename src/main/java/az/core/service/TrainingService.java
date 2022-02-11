package az.core.service;

import az.core.model.dto.request.TrainingRequestDto;
import az.core.model.dto.response.TrainingResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TrainingService {

    List<TrainingResponseDto> getAllTraining();

    TrainingResponseDto getById(Long id);

    TrainingResponseDto addTraining(TrainingRequestDto trainingRequestDto);

    TrainingResponseDto updateTraining(Long id, TrainingRequestDto trainingRequestDto);

    TrainingResponseDto deleteTraining(Long id);

    String uploadImage(MultipartFile file, Long id);

    String updateImage(MultipartFile file, Long id);

    void deleteUserImage(Long id);

    void deleteFile(String fileName, String folder);

    byte[] getFile(String fileName, String folder);

    TrainingResponseDto getByUrl(String url);
}
