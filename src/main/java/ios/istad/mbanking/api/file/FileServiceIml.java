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
    @Value("file.download-url")
    private String fileDownload;
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
                .downloadUrl(String.format("%s%s",fileDownload ,name))
                .size(resource.contentLength())
                .build();
    }
    @Override
    public void deleteByName(String name) {
        fileUtil.deleteByName(name);
    }

    @Override
    public Resource downloadFileName(String name) {
        return fileUtil.findByName(name);
    }

}












