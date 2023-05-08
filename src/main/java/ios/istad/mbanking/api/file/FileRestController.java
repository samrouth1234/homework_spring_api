package ios.istad.mbanking.api.file;
import ios.istad.mbanking.BaseRest.BaseRest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@Slf4j
@RequiredArgsConstructor
public class FileRestController {
    private final FileService fileService;
    @PostMapping
    public BaseRest<?>uploadSingle(@RequestPart("file") MultipartFile file){
        log.info("File Request ={}",file);
        FileDto fileDto=fileService.uploadSingle(file);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File upload Single File successfully!")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
    @PostMapping("/multiple")
    public BaseRest<?>uploadMultiFile(@RequestPart List<MultipartFile>files){

        log.info("File Request ={}",files);
        List<FileDto> filesDto =fileService.uploadMultiple(files);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File upload Multiple File successfully!")
                .timestamp(LocalDateTime.now())
                .data(filesDto)
                .build();
    }
    @DeleteMapping("/{filename}")
    public BaseRest<?> deleteFileByName(@PathVariable String filename){
        FileDto fileDto = fileService.deletedFileByName(filename);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been deleted successfully")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }

    @GetMapping
    public BaseRest<?> findAllFile() {
        List<FileDto> resultFiles = fileService.findAllFile();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been finAll successfully")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

    @DeleteMapping
    public BaseRest<?> deleteAllFile(){
        boolean resultFiles = fileService.deletedAllFiles();
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Deleted All File has been successfully..")
                .timestamp(LocalDateTime.now())
                .data(resultFiles)
                .build();
    }

}
