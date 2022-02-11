package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.error.FileCantUploadException;
import az.core.mapper.TrainingMapper;
import az.core.model.dto.request.TrainingRequestDto;
import az.core.model.dto.response.TrainingResponseDto;
import az.core.model.entity.Training;
import az.core.model.entity.TrainingCategory;
import az.core.repository.TrainingCategoryRepository;
import az.core.repository.TrainingRepository;
import az.core.service.TrainingService;
import az.core.service.FilesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final TrainingRepository trainingRepository;
    private final FilesService filesService;
    private final TrainingMapper trainingMapper;
    private final TrainingCategoryRepository trainingCategoryRepository;

    @Value("${minio.image-folder}")
    private String imageFolder;

    @Override
    public List<TrainingResponseDto> getAllTraining() {
        log.info("getAllTraining requesting...");
        List<Training> trainings = trainingRepository.findAll();
        log.info("getAllTraining Response started with: {}", kv("trainings", trainings));
        return trainingMapper.entitiesToDto(trainings);

    }

    @Override
    public TrainingResponseDto getById(Long id) {
        log.info("getById Blog started with: {}", kv("id", id));
        Training training = trainingRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Training.class, id);
        });
        TrainingResponseDto response = trainingMapper.entityToDto(training);
        log.info("getById Training completed successfully with: {}", kv("id", id));
        return response;
    }

    @Override
    public TrainingResponseDto getByUrl(String url) {
        log.info("getByUrl Blog started with: {}", kv("url", url));
        Training training = trainingRepository.findByUrl(url).orElseThrow(() -> {
            throw new EntityNotFoundException(Training.class, url);
        });
        TrainingResponseDto response = trainingMapper.entityToDto(training);
        log.info("getByUrl Training completed successfully with: {}", kv("url", url));
        return response;
    }

    @Override
    @Transactional
    public TrainingResponseDto addTraining(TrainingRequestDto trainingRequestDto) {
        log.info("create Training started with:{}", trainingRequestDto);
        log.info("Find Category By Id started with: {}", kv("name", trainingRequestDto.getCategoryId()));
        TrainingCategory category = trainingCategoryRepository.findById(trainingRequestDto.getCategoryId()).orElseThrow(() -> {
            throw new EntityNotFoundException(TrainingCategory.class);
        });
        log.info("Find Category By Id Response started with: {}", kv("name", trainingRequestDto.getCategoryId()));
        Training training = trainingMapper.dtoToEntity(trainingRequestDto);
        training.setTrainingCategory(category);
        trainingRepository.save(training);
        TrainingResponseDto trainingResponseDto = trainingMapper.entityToDto(training);
        log.info("create Training completed successfully with: {}", kv("blogDto", trainingRequestDto));
        return trainingResponseDto;
    }

    @Override
    public TrainingResponseDto updateTraining(Long id, TrainingRequestDto trainingRequestDto) {
        log.info("update Training started with: {}, {}", kv("id", id),
                kv("trainingRequestDto", trainingRequestDto));
        Training training = trainingRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Training.class, id);
        });

        if (training != null) {
            String image = training.getImage();
            training = trainingMapper.dtoToEntity(trainingRequestDto);
            training.setId(id);
            training.setImage(image);
            log.info("Find Category By Name Response started with: {}", kv("name", trainingRequestDto.getCategoryId()));
            TrainingCategory category = trainingCategoryRepository.findById(trainingRequestDto.getCategoryId()).orElseThrow(() -> {
                throw new EntityNotFoundException(TrainingCategory.class);
            });
            training.setTrainingCategory(category);
        }
        trainingRepository.save(training);
        TrainingResponseDto responseDto = trainingMapper.entityToDto(training);
        log.info("update Training completed successfully with: {}, {}", kv("id", id),
                kv("blogDto", trainingRequestDto));
        return responseDto;

    }

    @Override
    public TrainingResponseDto deleteTraining(Long id) {
        log.info("delete Training started with: {}", kv("id", id));
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(Training.class, id);
                }
        );
        if (training.getImage() != null) {
            deleteFile(training.getImage(), imageFolder);
        }
        trainingRepository.delete(training);
        TrainingResponseDto trainingResponseDto = trainingMapper.entityToDto(training);
        log.info("delete Blog completed successfully with: {}", kv("id", id));
        return trainingResponseDto;
    }


    @Override
    public String uploadImage(MultipartFile file, Long id) {
        log.info("uploadImage to Training started with, partnerId: {}", id);
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Training.class, id));
        if (training.getImage() == null) {
            String fileName = filesService.uploadImage(file, imageFolder);
            training.setImage(fileName);
            trainingRepository.save(training);
            log.info("uploadImage to Training completed successfully with partnerId:{}", id);
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }

    @Override
    public String updateImage(MultipartFile file, Long id) {
        log.info("updateImage to Training started with, {}",
                kv("partnerId", id));
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Training.class, id));
        deleteFile(training.getImage(), imageFolder);
        String fileName = filesService.uploadImage(file, imageFolder);
        training.setImage(fileName);
        trainingRepository.save(training);
        log.info("updateImage to Training completed successfully with {}",
                kv("partnerId", training));
        return fileName;
    }

    @Override
    public void deleteUserImage(Long id) {
        log.info("deleteUserImage started from Training with {}", kv("id", id));
        Training training = trainingRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Training.class, id));
        if (training.getImage() != null) {
            filesService.deleteFile(training.getImage(), imageFolder);
            training.setImage(null);
            trainingRepository.save(training);
        }
        log.info("deleteUserImage completed successfully from Training with {} ", kv("id", id));
    }

    @Override
    public void deleteFile(String fileName, String folder) {

    }

    @Override
    public byte[] getFile(String fileName, String folder) {
        log.info("getFile started with {}", kv("fileName", fileName));
        return filesService.getFile(fileName, folder);
    }



}

