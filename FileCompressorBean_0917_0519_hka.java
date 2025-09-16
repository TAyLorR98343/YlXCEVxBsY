// 代码生成时间: 2025-09-17 05:19:50
 * It includes error handling and ensures the code is maintainable and extensible.
 */
package com.example.filecompressor;

import java.io.*;
import java.util.zip.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;

@ManagedBean
@ViewScoped
public class FileCompressorBean {

    private String compressedFilePath;
    private String decompressedFilePath;
    private String operation; // 'compress' or 'decompress'

    // Setters and getters for the bean properties
    public String getCompressedFilePath() {
        return compressedFilePath;
    }

    public void setCompressedFilePath(String compressedFilePath) {
        this.compressedFilePath = compressedFilePath;
    }

    public String getDecompressedFilePath() {
        return decompressedFilePath;
    }

    public void setDecompressedFilePath(String decompressedFilePath) {
        this.decompressedFilePath = decompressedFilePath;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    /**
     * Compress a file using ZIP algorithm.
     */
    public void compressFile() {
        try {
            File inputFile = new File(compressedFilePath);
            FileOutputStream fos = new FileOutputStream(inputFile.getName() + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            zos.putNextEntry(new ZipEntry(inputFile.getName()));
            FileInputStream fis = new FileInputStream(inputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            fis.close();
            zos.closeEntry();
            zos.close();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.Severity.INFO, "File Compressed Successfully", ""));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.Severity.ERROR, "Error Compressing File", e.getMessage()));
        }
    }

    /**
     * Decompress a ZIP file.
     */
    public void decompressFile() {
        try {
            File zipFile = new File(compressedFilePath);
            FileInputStream fis = new FileInputStream(zipFile);
            ZipInputStream zis = new ZipInputStream(fis);
            ZipEntry zipEntry = zis.getNextEntry();            
            File outputFile = new File(decompressedFilePath + File.separator + zipEntry.getName());
            FileOutputStream fos = new FileOutputStream(outputFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, length);
            }
            zis.closeEntry();
            fos.close();
            zis.close();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.Severity.INFO, "File Decompressed Successfully", ""));
        } catch (IOException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.Severity.ERROR, "Error Decompressing File", e.getMessage()));
        }
    }

    // Additional methods can be added here for more functionality
}
