package az.core.service.impl;

import az.core.error.EntityNotFoundException;
import az.core.error.FileCantUploadException;
import az.core.mapper.CourseMapper;
import az.core.model.dto.request.CourseRequestDto;
import az.core.model.dto.response.CourseResponseDto;
import az.core.model.entity.Course;
import az.core.model.entity.CourseCategory;
import az.core.repository.CourseCategoryRepository;
import az.core.repository.CourseRepository;
import az.core.service.CourseService;
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
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final FilesService filesService;
    private final CourseMapper courseMapper;
    private final CourseCategoryRepository courseCategoryRepository;

    @Value("${minio.image-folder}")
    private String imageFolder;

    @Override
    public List<CourseResponseDto> getAllCourse() {
        log.info("getAllCourse requesting...");
        List<Course> courses = courseRepository.findAll();
        log.info("getAllCourse Response started with: {}", kv("courses", courses));
        return courseMapper.entitiesToDto(courses);

    }

    @Override
    public CourseResponseDto getById(Long id) {
        log.info("getById Blog started with: {}", kv("id", id));
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Course.class, id);
        });
        CourseResponseDto response = courseMapper.entityToDto(course);
        log.info("getById Course completed successfully with: {}", kv("id", id));
        return response;
    }

    @Override
    @Transactional
    public CourseResponseDto addCourse(CourseRequestDto courseRequestDto) {
        log.info("create Course started with:{}", courseRequestDto);
        log.info("Find Category By Name started with: {}", kv("name", courseRequestDto.getCourseCategory()));
        CourseCategory category = courseCategoryRepository.findByName(courseRequestDto.getCourseCategory()).orElseThrow(() -> {
            throw new EntityNotFoundException(CourseCategory.class);
        });
        log.info("Find Category By Name Response started with: {}", kv("name", courseRequestDto.getCourseCategory()));
        Course course = courseMapper.dtoToEntity(courseRequestDto);
        course.setCourseCategory(category);
        courseRepository.save(course);
        CourseResponseDto courseResponseDto = courseMapper.entityToDto(course);
        log.info("create Course completed successfully with: {}", kv("blogDto", courseRequestDto));
        return courseResponseDto;
    }

    @Override
    public CourseResponseDto updateCourse(Long id, CourseRequestDto courseRequestDto) {
        log.info("update Course started with: {}, {}", kv("id", id),
                kv("courseRequestDto", courseRequestDto));
        Course course = courseRepository.findById(id).orElseThrow(() -> {
            throw new EntityNotFoundException(Course.class, id);
        });

        if (course != null) {
            course = courseMapper.dtoToEntity(courseRequestDto);
            course.setId(id);
            log.info("Find Category By Name Response started with: {}", kv("name", courseRequestDto.getCourseCategory()));
            CourseCategory category = courseCategoryRepository.findByName(courseRequestDto.getCourseCategory()).orElseThrow(() -> {
                throw new EntityNotFoundException(CourseCategory.class);
            });
            course.setCourseCategory(category);
        }
        courseRepository.save(course);
        CourseResponseDto responseDto = courseMapper.entityToDto(course);
        log.info("update Course completed successfully with: {}, {}", kv("id", id),
                kv("blogDto", courseRequestDto));
        return responseDto;

    }

    @Override
    public CourseResponseDto deleteCourse(Long id) {
        log.info("delete Course started with: {}", kv("id", id));
        Course course = courseRepository.findById(id).orElseThrow(
                () -> {
                    throw new EntityNotFoundException(Course.class, id);
                }
        );
        if (course.getImage() != null) {
            deleteFile(course.getImage(), imageFolder);
        }
        courseRepository.delete(course);
        CourseResponseDto courseResponseDto = courseMapper.entityToDto(course);
        log.info("delete Blog completed successfully with: {}", kv("id", id));
        return courseResponseDto;
    }


    @Override
    public String uploadImage(MultipartFile file, Long id) {
        log.info("uploadImage to Course started with, partnerId: {}", id);
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Course.class, id));
        if (course.getImage() == null) {
            String fileName = filesService.uploadImage(file, imageFolder);
            course.setImage(fileName);
            courseRepository.save(course);
            log.info("uploadImage to Course completed successfully with partnerId:{}", id);
            return fileName;
        }
        throw new FileCantUploadException(file.getOriginalFilename());
    }

    @Override
    public String updateImage(MultipartFile file, Long id) {
        log.info("updateImage to Course started with, {}",
                kv("partnerId", id));
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Course.class, id));
        deleteFile(course.getImage(), imageFolder);
        String fileName = filesService.uploadImage(file, imageFolder);
        course.setImage(fileName);
        courseRepository.save(course);
        log.info("updateImage to Course completed successfully with {}",
                kv("partnerId", course));
        return fileName;
    }

    @Override
    public void deleteUserImage(Long id) {
        log.info("deleteUserImage started from Course with {}", kv("id", id));
        Course course = courseRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(Course.class, id));
        if (course.getImage() != null) {
            filesService.deleteFile(course.getImage(), imageFolder);
            course.setImage(null);
            courseRepository.save(course);
        }
        log.info("deleteUserImage completed successfully from Course with {} ", kv("id", id));
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

