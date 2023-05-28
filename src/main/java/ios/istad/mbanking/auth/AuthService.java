package ios.istad.mbanking.auth;

import ios.istad.mbanking.auth.web.AuthDto;
import ios.istad.mbanking.auth.web.LogInDto;
import ios.istad.mbanking.auth.web.RegisterDto;

public interface AuthService {
    void register(RegisterDto registerDto);

    void verify(String email);

    void checkVerify(String email,String verifiedCode);

    AuthDto login(LogInDto logInDto);

}

