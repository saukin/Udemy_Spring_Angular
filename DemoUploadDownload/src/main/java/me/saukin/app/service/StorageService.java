/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.saukin.app.service;

import java.util.List;
import me.saukin.app.model.CustomFile;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author saukin
 */
public interface StorageService {
    
    String uploadOne(MultipartFile file);
    
    public Resource loadFileAsResource(String fileName);
          
    List<CustomFile> getFilesList();
//    List<UploadFileResponse> uploadMany(MultipartFile file);
    
    
}
