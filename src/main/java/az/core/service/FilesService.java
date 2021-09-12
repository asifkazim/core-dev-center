package az.core.service;

import org.springframework.web.multipart.MultipartFile;

public interface FilesService {



    String uploadImage(MultipartFile file, String folder);

    byte[] getFile(String fileName, String folder);

    void deleteFile(String fileName, String folder);



}
