package ios.istad.mbanking.api.file;
import ios.istad.mbanking.BaseRest.BaseRest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.time.LocalDateTime;
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
    @GetMapping("/{name}")
    public BaseRest<?> findByName(@PathVariable String name) throws IOException {
        FileDto fileDto =fileService.findByName(name);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("File has been Found  successfully")
                .timestamp(LocalDateTime.now())
                .data(fileDto)
                .build();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{name}")
    public void deleteByName(@PathVariable String name){
        fileService.deleteByName(name);
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
    @GetMapping("/download/{name}")
    public ResponseEntity<?> downloadFileName(@PathVariable String name){
        Resource resource = fileService.downloadFileName(name);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header("Content-Disposition","attachment; filename =" +resource.getFilename())
                .body(resource);
    }

}
