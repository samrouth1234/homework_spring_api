package ios.istad.mbanking;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class DevopsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DevopsApiApplication.class, args);
    }

    @GetMapping("/test")
    public String testVerifyView() {
        return "auth/verify";
    }

}
