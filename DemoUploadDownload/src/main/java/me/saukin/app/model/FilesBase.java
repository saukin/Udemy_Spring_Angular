package me.saukin.app.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;

/**
 *
 * @author saukin
 */
public class FilesBase {

    private static List<String> fileBase;

    private FilesBase() {
        fileBase = new ArrayList<>();
        File theDir = new File(".\\filebase\\");
        theDir.mkdirs();
        synchronized (theDir) {
            for (String file : theDir.list()) {
                fileBase.add(file);
            }
        }
    }

    private static class BaseHolder {
        private static final FilesBase INSTANCE = new FilesBase();
    }
    
    public List<String> getBase() {
        return FilesBase.fileBase;
    }
    
    public static FilesBase getInstance() {
        return BaseHolder.INSTANCE;
    }
    
    public void updateList(String fileName) {
        FilesBase.fileBase.add(fileName);
    }
    
    
}
