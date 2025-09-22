// 代码生成时间: 2025-09-22 15:39:56
 * It demonstrates error handling, comments, and best practices for code maintainability and extensibility.
 */

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ManagedBean
@SessionScoped
public class BatchFileRenamer {
    private String directoryPath;
    private String regexPattern;
    private String replacement;
    private List<File> selectedFiles;

    // Getters and setters for the properties
    public String getDirectoryPath() {
        return directoryPath;
    }
    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getRegexPattern() {
        return regexPattern;
    }
    public void setRegexPattern(String regexPattern) {
        this.regexPattern = regexPattern;
    }

    public String getReplacement() {
        return replacement;
    }
    public void setReplacement(String replacement) {
        this.replacement = replacement;
    }

    public List<File> getSelectedFiles() {
        return selectedFiles;
    }
    public void setSelectedFiles(List<File> selectedFiles) {
        this.selectedFiles = selectedFiles;
    }

    /**
     * Renames files in the specified directory based on the provided regex pattern and replacement.
     * @return A message indicating the result of the operation.
     */
    public String renameFiles() {
        StringBuilder resultMessage = new StringBuilder();
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            resultMessage.append("The provided path is not a directory.
");
            return resultMessage.toString();
        }

        Pattern pattern = Pattern.compile(regexPattern);
        for (File file : selectedFiles) {
            try {
                String fileName = file.getName();
                Matcher matcher = pattern.matcher(fileName);
                if (matcher.find()) {
                    String newFileName = matcher.replaceFirst(replacement);
                    Path oldPath = file.toPath();
                    Path newPath = Paths.get(directoryPath, newFileName);
                    Files.move(oldPath, newPath);
                    resultMessage.append("Renamed: ").append(fileName).append(" to ").append(newFileName).append("
");
                } else {
                    resultMessage.append("No match found for: ").append(fileName).append("
");
                }
            } catch (IOException e) {
                resultMessage.append("Error renaming file: ").append(file.getName()).append("
");
            }
        }
        return resultMessage.toString();
    }
}
