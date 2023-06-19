package ios.istad.mbanking.auth;

import ios.istad.mbanking.auth.web.AuthDto;
import ios.istad.mbanking.auth.web.LogInDto;
import ios.istad.mbanking.auth.web.RegisterDto;
import ios.istad.mbanking.auth.web.TokenDto;

public interface AuthService {
    AuthDto refreshToken(TokenDto tokenDto);
    void register(RegisterDto registerDto);

    void verify(String email);

    void checkVerify(String email,String verifiedCode);

    AuthDto login(LogInDto logInDto);

}

