package ios.istad.mbanking.api.file;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface FileService {
    /**
     * used upload single file
     * @param file request from data from clinet
     * @return fileDto
     */
    FileDto uploadSingle(MultipartFile file);

    /**
     * used upload multiple file
     * @param files request from data from client
     * @return fileDto
     */
    List<FileDto> uploadMultiple(List<MultipartFile>files);

/**
 * used deleted  file
 * @param filename request from data from client
 * @return fileDto
 */
    FileDto deletedFileByName (String filename );

    /**
     * used findAllFile
     * @return request from data from client
     * fileDto
     */

    List<FileDto>findAllFile();

    /**
     * used deleted All files
     * request from data from client
     * fileDto
     */
    boolean deletedAllFiles();

    /**
     * used downLoadFile
     *
     * @param filename
     * @param filename request from data from client
     * @return fileDto
     */

    FileDto downloadFileName(String filename);

}
