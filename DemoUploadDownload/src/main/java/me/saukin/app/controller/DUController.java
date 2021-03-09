/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.app.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import me.saukin.app.model.CustomFile;
import me.saukin.app.service.FileStorageService;
import me.saukin.app.utils.UploadResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author saukin
 */

@RestController
@RequestMapping(value = "/du")
public class DUController {
    
    @Autowired
    FileStorageService fileStorageService;
    
       
    
    @GetMapping
    public List<CustomFile> getFilesList() {
        return fileStorageService.getFilesList();
    }
    
    @PostMapping(value = "/upload")
    public UploadResponseBody uploadOne(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.uploadOne(file);
        
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(".\\filebase\\")
                .path(fileName)
                .toUriString();
        
        return new UploadResponseBody(fileName, fileDownloadUri, 
                file.getContentType(), file.getSize());
    }
    
    @PostMapping(value = "/uploadmany")
    public List<UploadResponseBody> uploadMany(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadOne(file))
                .collect(Collectors.toList());
    }
    
    @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, 
            HttpServletRequest request) {
        //Load the file as a resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);
        
        //Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            System.out.println(("Could not determine file type."));	
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
    
    @GetMapping(value = "/temp")
    public ResponseEntity<String> getTemp() {
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.SECONDS);
        int temp = (int) (Math.random() * 50) + 10;
        String response = String.format("<h3>Current tempreture : %d Degrees<h3>"
                + "<h3>Response from server %s </h3>"
                + "<a href = ''>Once more</a>"
                , temp, LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        
        return ResponseEntity.ok().cacheControl(cacheControl).body(response);
    }
    
    @GetMapping(value = "/{id}")
    public CustomFile getFileById(@PathVariable(value = "id") int id) {
        return fileStorageService.getFile(id);
    }
    
}
