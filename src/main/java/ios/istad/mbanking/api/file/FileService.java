package ios.istad.mbanking.api.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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
     * find by name
     * @param name request from data from client
     * @return fileDto
     */

    FileDto findByName(String name) throws IOException;


    /**
     * used deleted by name
     * @param name
     * fileDto
     */
    void deleteByName( String name);


    List <FileDto> deletedAllFile();


    /**
     * used findAllFile
     * @return request from data from client
     * fileDto
     */

    List<FileDto>findAllFile() throws MalformedURLException;

    /**
     * used downLoadFile
     * @param name
     * @param name request from data from client
     * @return fileDto
     */

    Resource downloadFileName(String name);

}
