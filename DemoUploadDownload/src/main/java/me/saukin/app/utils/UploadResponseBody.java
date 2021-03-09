package me.saukin.app.utils;

/**
 *
 * @author saukin
 */
public class UploadResponseBody {
    
    private String fileName;
    private String fileDownloadURi;
    private String contentType;
    private long fileSize;

    public UploadResponseBody(String fileName, String fileDownloadURi, String contentType, long fileSize) {
        this.fileName = fileName;
        this.fileDownloadURi = fileDownloadURi;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadURi() {
        return fileDownloadURi;
    }

    public void setFileDownloadURi(String fileDownloadURi) {
        this.fileDownloadURi = fileDownloadURi;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }
    
    
    
    
}
