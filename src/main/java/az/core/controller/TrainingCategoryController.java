package az.core.controller;

import az.core.model.dto.request.TrainingCategoryRequestDto;
import az.core.model.dto.response.TrainingCategoryResponseDto;
import az.core.service.TrainingCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trainings/categories")
@RequiredArgsConstructor
public class TrainingCategoryController {

    private final TrainingCategoryService trainingCategoryService;

    @GetMapping
    public ResponseEntity<List<TrainingCategoryResponseDto>> getAllCategories() {
        List<TrainingCategoryResponseDto> categories = trainingCategoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingCategoryResponseDto> getById(@PathVariable Long id) {
        TrainingCategoryResponseDto categoryDto = trainingCategoryService.getById(id);
        return ResponseEntity.ok(categoryDto);
    }

    @PostMapping()
    public ResponseEntity<TrainingCategoryResponseDto> addTrainingCategory(@RequestBody TrainingCategoryRequestDto trainingCategoryRequestDto) {
        TrainingCategoryResponseDto responseDto = trainingCategoryService.addCategory(trainingCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrainingCategoryResponseDto> updateTrainingCategory(@PathVariable Long id, @RequestBody TrainingCategoryRequestDto trainingCategoryRequestDto) {
        TrainingCategoryResponseDto responseDto = trainingCategoryService.updateCategory(id, trainingCategoryRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TrainingCategoryResponseDto> deleteTrainingCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingCategoryService.deleteCategory(id));
    }
}
