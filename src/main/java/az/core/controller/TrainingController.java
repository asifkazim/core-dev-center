package az.core.controller;

import az.core.model.dto.request.TrainingRequestDto;
import az.core.model.dto.response.TrainingResponseDto;
import az.core.model.dto.FileDto;
import az.core.service.TrainingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/trainings")
public class TrainingController {

    private final TrainingService trainingService;

    @Value("${minio.image-folder}")
    private String imageFolder;


    @GetMapping
    public ResponseEntity<List<TrainingResponseDto>> getAllTrainings() {
        List<TrainingResponseDto> trainings = trainingService.getAllTraining();
        return ResponseEntity.ok(trainings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingResponseDto> getById(@PathVariable Long id) {
        TrainingResponseDto training = trainingService.getById(id);
        return ResponseEntity.ok(training);
    }

    @GetMapping("/urls/{url}")
    public ResponseEntity<TrainingResponseDto> getById(@PathVariable String url) {
        TrainingResponseDto training = trainingService.getByUrl(url);
        return ResponseEntity.ok(training);
    }

    @PostMapping
    public ResponseEntity<TrainingResponseDto> addTraining(@RequestBody TrainingRequestDto trainingRequestDto) {
        TrainingResponseDto training = trainingService.addTraining(trainingRequestDto);
        return ResponseEntity.ok(training);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingResponseDto> updateTraining(@PathVariable Long id, @RequestBody TrainingRequestDto trainingRequestDto) {
        TrainingResponseDto training = trainingService.updateTraining(id, trainingRequestDto);
        return ResponseEntity.ok(training);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainingResponseDto> deleteTraining(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.deleteTraining(id));
    }

    @PostMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> createImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(trainingService.uploadImage(file, id)));
    }

    @PutMapping(value = "/image/{id}")
    public ResponseEntity<FileDto> updateImage(@PathVariable Long id, @Valid @RequestParam MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new FileDto(trainingService.updateImage(file, id)));
    }

    @GetMapping(value = "/image/{fileName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@PathVariable("fileName") String fileName) {
        return trainingService.getFile(fileName, imageFolder);
    }

    @DeleteMapping("/image/{id}")
    public void deleteUserImage(@PathVariable("id") Long id) {
        trainingService.deleteUserImage(id);
    }
}
