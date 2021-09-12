package az.core.service.impl;

import az.core.service.FilesService;
import az.core.util.FilesUtil;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.messages.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static net.logstash.logback.argument.StructuredArguments.kv;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilesServiceImpl implements FilesService {
    private final MinioClient minioClient;
    private final FilesUtil filesUtil;

    @Value("${minio.bucket}")
    private String bucketName;
    private final String IMAGE_MEDIA_TYPE = "image";


    @SneakyThrows
    @Override
    public String uploadImage(MultipartFile file, String folder) {
        String fileExtension = filesUtil.getFileExtensionIfAcceptable(file, IMAGE_MEDIA_TYPE);
        String fileName = filesUtil.generateUniqueName(fileExtension);
        String objectName = folder + fileName;

        BufferedImage image = ImageIO.read(file.getInputStream());
        int width = image.getWidth();
        int height = image.getHeight();
        if (width > 2560 && height > 1080) {
            width = width / 3;
            height = height / 3;
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(resizeImage(image, width, height), fileExtension, byteArrayOutputStream);
        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).stream(
                inputStream, inputStream.available(), -1)
                .contentType(file.getContentType())
                .build());
        return fileName;
    }

    @SneakyThrows
    @Override
    public byte[] getFile(String fileName, String folder) {
        String objectName = folder + fileName;
        GetObjectArgs minioRequest = GetObjectArgs.builder().bucket(bucketName).object(objectName).build();
        byte[] bytes = null;
        try {
            bytes = minioClient.getObject(minioRequest).readAllBytes();
        } catch (ErrorResponseException e) {
            ErrorResponse response = e.errorResponse();
            log.error("Minio error occurred with: {}, {}, {}",
                    kv("code", response.code()), kv("message", response.message()),
                    kv("objectName", response.objectName()));
        }
        return bytes;    }

    @SneakyThrows
    @Override
    public void deleteFile(String fileName, String folder) {
        String objectName = folder + fileName;
        minioClient.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(objectName).build());
    }

    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }





}