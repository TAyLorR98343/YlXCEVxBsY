// 代码生成时间: 2025-09-17 17:18:35
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 文件解压工具类，用于解压ZIP格式的压缩文件。
 * @author Your Name
 * @version 1.0
 */
public class FileUnzipUtility {

    /**
     * 解压ZIP文件到指定目录。
     * @param zipFilePath 压缩文件的路径。
     * @param destDirectory 解压后的文件存放目录。
     * @throws IOException 如果文件读写发生错误。
     */
    public void unzip(String zipFilePath, String destDirectory) throws IOException {
        File destDir = new File(destDirectory);
        if (!destDir.exists()) {
            destDir.mkdir();
        }

        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();

        // 遍历压缩文件中的所有文件和文件夹
        while (entry != null) {
            String filePath = destDirectory + File.separator + entry.getName();
            if (!entry.isDirectory()) {
                // 如果是文件，则解压到指定目录
                extractFile(zipIn, filePath);
            } else {
                // 如果是文件夹，则创建文件夹
                File dir = new File(filePath);
                dir.mkdir();
            }
            zipIn.closeEntry();
            entry = zipIn.getNextEntry();
        }
        zipIn.close();
    }

    /**
     * 从ZIP输入流中提取文件。
     * @param zipIn ZIP输入流。
     * @param filePath 文件的完整路径。
     * @throws IOException 如果文件读写发生错误。
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[4096];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    // 主方法，用于测试解压功能
    public static void main(String[] args) {
        FileUnzipUtility unzipper = new FileUnzipUtility();
        try {
            // 测试解压ZIP文件到指定目录
            unzipper.unzip("path/to/your/zipfile.zip", "path/to/destination/folder");
            System.out.println("解压完成。");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
