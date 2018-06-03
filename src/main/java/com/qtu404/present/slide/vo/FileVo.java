package com.qtu404.present.slide.vo;

import java.io.File;

public class FileVo {
    private String fileName;
    private String fileContentType;
    private File file;
    private String fileSaveDirPath;
    private String size;
    private String contextPath;
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getRealPath() {
        return contextPath + "/" + fileSaveDirPath + userId + "/" + fileName;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getFileSavePath() {
        return fileSaveDirPath + "/" + userId + "/" + fileName;
    }


    public String getFileSaveDirPath() {
        return fileSaveDirPath;
    }

    public void setFileSaveDirPath(String fileSaveDirPath) {
        this.fileSaveDirPath = fileSaveDirPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
