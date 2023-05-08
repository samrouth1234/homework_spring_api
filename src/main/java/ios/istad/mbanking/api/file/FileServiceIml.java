package ios.istad.mbanking.api.file;

import ios.istad.mbanking.util.FileUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class FileServiceIml implements FileService{
    @Value("${file.server-path}")
    private String fileServerPath;

    @Value("${file.base-url}")
    private String fileBaseUrl;

    @Value("${file.domain}")
    private String fileDomain;

    @Value("${file.download-url}")
    private String fileDownloadUrl;

    @Autowired
    private ResourceLoader resourceLoader;

    private FileUtil fileUtil;


    @Autowired
    public void setFileDtoUtil(FileUtil fileUtil){
        this.fileUtil=fileUtil;
    }
    @Override
    public FileDto uploadSingle(MultipartFile file) {
        return fileUtil.upload(file);
    }
    @Override
    public List<FileDto> uploadMultiple(List<MultipartFile> files) {
        List<FileDto>filesDto = new ArrayList<>();
        for(MultipartFile file :files){
            filesDto.add(fileUtil.upload(file));
        }
        return filesDto;
    }

    @Override
    public FileDto deletedFileByName(String filename) {
        FileDto fileDto = this.findAllFile().stream()
                .filter(fileDtos -> fileDtos.name().equalsIgnoreCase(filename))
                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "File not be found, please try gain..."));

        File file = new File(this.fileServerPath, filename);
        file.delete();
        boolean delete = file.delete();
        return fileDto;
    }

    @Override
    public List<FileDto> findAllFile() {
        File file = new File(fileServerPath);
        if (Objects.requireNonNull(file.list()).length > 0) {
            List<File> fileList = new ArrayList<>(List.of(Objects.requireNonNull(file.listFiles())));
            List<FileDto> resultList = new ArrayList<>();
            for (File files : fileList) {
                String extension = fileUtil.getExtensionFile(file);
                resultList.add(
                        FileDto.builder()
                                .name(files.getName())
                                .url(fileBaseUrl + files.getName())
                                .extension(extension)
                                .downloadUrl(fileDownloadUrl + files.getName())
                                .extension(fileUtil.getExtensionFile(files))
                                .size(files.length())
                                .build()
                );
            }
            return resultList;
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "find file not found"
        );
    }

    @Override
    public boolean deletedAllFiles() {
        Resource resource = resourceLoader.getResource("file:" + fileServerPath);
        try {
            File locationFile = ResourceUtils.getFile(resource.getURL());
            System.out.println(locationFile);
            FileUtils.deleteDirectory(locationFile);
            return true;
        } catch (IOException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "delete file fail....! "
            );
        }
    }

    @Override
    public FileDto downloadFileName(String filename){

        return null;
    }

}

