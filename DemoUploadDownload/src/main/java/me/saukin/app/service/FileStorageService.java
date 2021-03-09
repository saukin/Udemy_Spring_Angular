package me.saukin.app.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import me.saukin.app.model.CustomFile;
import me.saukin.app.model.FilesBase;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
/**
 *
 * @author saukin
 */
@Service
public class FileStorageService implements StorageService {

    Path fileStorageLocation;

    FilesBase fb = FilesBase.getInstance();

    @Override
    public List<CustomFile> getFilesList() {
        
        List<CustomFile> listFiles = new ArrayList();
        int i = 1;
        for (String f : fb.getBase()) {
            listFiles.add(new CustomFile(i++, f));
        }
        return listFiles;
    }

    public FileStorageService() {
        this.fileStorageLocation = Paths.get(".\\filebase\\").toAbsolutePath().normalize();
    }

    @Override
    public String uploadOne(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            //checking for bad characters
            if (fileName.contains("..")) {
                //throw Exception
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            fb.updateList(fileName);
            return fileName;
        } catch (IOException ioe) {
            // handle exception
        }
        
        return fileName;
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(!resource.exists()) {
                System.out.println("NOT EXIST()");
                //throw new MyFileNotFoundException("File not found " + fileName);
            } else {
                return resource;
            }
        } catch (MalformedURLException ex) {
            //throw new MyFileNotFoundException("File not found " + fileName, ex);
        }

        return null;
    }

    public CustomFile getFile(int id) {
        return new CustomFile(id, fb.getBase().get(id));
    }
}
