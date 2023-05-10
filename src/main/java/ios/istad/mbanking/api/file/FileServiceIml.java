package ios.istad.mbanking.api.file;

import ios.istad.mbanking.util.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class FileServiceIml implements FileService{
    @Value("${file.server-path}")
    private String fileServerPath;

    @Value("${file.base-url}")
    private String fileBaseUrl;
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
    public FileDto findByName(String name) throws IOException {
       Resource resource = fileUtil.findByName(name);
        return FileDto.builder()
                .name(resource.getFilename())
                .extension(fileUtil.getExtension(resource.getFilename()))
                .url(String.format("%s%s",fileUtil.getFileBaseUrl(),resource.getFilename()))
                .size(resource.contentLength())
                .build();
    }

    @Override
    public void deleteByName(String name) {
        fileUtil.deleteByName(name);
    }

    @Override
    public List<FileDto> deletedAllFile() {
        return null;
    }
    @Override
    public List<FileDto> findAllFile() {
        List<FileDto> fileDtoList = new ArrayList<>();
        File folder = new File(fileUtil.getFileServerPath());
        File[] listOfFiles = folder.listFiles();
        assert listOfFiles != null;
        for (File file : listOfFiles) {
            if (file.isFile()) {
                String name = file.getName();
                String url = fileUtil.getFileBaseUrl() + name;
                long size = file.length();
                String downloadUrl =downloadFileName(name) + name;
                int lastDotIndex = name.lastIndexOf(".");
                String extension = name.substring(lastDotIndex + 1);
            }
        }
        return fileDtoList;
    }

    @Override
    public Resource downloadFileName(String name) {
        return fileUtil.findByName(name);
    }

}












