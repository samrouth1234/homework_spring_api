package ios.istad.mbanking.auth;

import ios.istad.mbanking.BaseRest.BaseRest;
import ios.istad.mbanking.auth.web.AuthDto;
import ios.istad.mbanking.auth.web.LogInDto;
import ios.istad.mbanking.auth.web.RegisterDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {
    private final AuthService authService;
    @PostMapping("/register")
    public BaseRest<?> register(@Valid @RequestBody  RegisterDto registerDto){
        // call service
        authService.register(registerDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been registered successfully")
                .timestamp(LocalDateTime.now())
                .data(registerDto.email())
                .build();
    }

    @PostMapping("/verify")
    public BaseRest<?> verify(@RequestParam String email) {
        authService.verify(email);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("Please check email and verify")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }

    @GetMapping("/check-verify")
    public BaseRest<?> checkVerify(@RequestParam String email,
                                   @RequestParam String verifiedCode){
        log.info("Email.: {}",email);
        log.info("Verified Code: {}", verifiedCode);

        authService.checkVerify(email, verifiedCode);

        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been successfully")
                .timestamp(LocalDateTime.now())
                .data(email)
                .build();
    }
    @PostMapping("/login")
    public BaseRest<?> login(@Valid @RequestBody LogInDto logInDto){
        // call service
        AuthDto authDto = authService.login(logInDto);
        //authService.login(logInDto);
        return BaseRest.builder()
                .status(true)
                .code(HttpStatus.OK.value())
                .message("You have been login successfully")
                .timestamp(LocalDateTime.now())
                .data(authDto)
                .build();
    }

}
