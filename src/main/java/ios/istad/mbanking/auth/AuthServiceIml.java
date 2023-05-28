package ios.istad.mbanking.auth;

import ios.istad.mbanking.api.user.User;
import ios.istad.mbanking.api.user.UserMapStruct;
import ios.istad.mbanking.auth.web.AuthDto;
import ios.istad.mbanking.auth.web.LogInDto;
import ios.istad.mbanking.auth.web.RegisterDto;
import ios.istad.mbanking.util.MailUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.UUID;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceIml implements AuthService{
    private final AuthMapper authMapper;
    private final UserMapStruct userMapStruct;
    private final PasswordEncoder encoder;

    // injection DaoAuthenticationProvider method security

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    // injection  MailUtil
    private final MailUtil mailUtil;
    //
    private final JwtEncoder jwtEncoder;

    // injection mail in application-dev.properties
    @Value("${spring.mail.username}")
    private String fromAppMail;

    @Override
    public void register(RegisterDto registerDto) {

        User user = userMapStruct.registerDtoToUser(registerDto);
        user.setIsVerified(false);
        user.setPassword(encoder.encode(user.getPassword()));

        log.info("User :{}",user.getEmail());

        if (authMapper.register(user)){
            // Create users role
            for (Integer roleId: registerDto.roleIds()){
                authMapper.createUserRole(user.getId(),roleId);
            }
        }
    }
    @Override
    public void verify(String email) {

        User user = authMapper.selectByEmail(email).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Email has not been found"));

        // set VerifiedCode before send mail
        String verifiedCode = UUID.randomUUID().toString();

        if (authMapper.updateVerifiedCode(email,verifiedCode)){
            user.setVerifiedCode(verifiedCode);
        }else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR
                    ,"User cannot be verified");
        }

        MailUtil.Meta <?> meta = MailUtil.Meta.builder()
                .to(email)
                .from(fromAppMail)
                .subject("Account Verification")
                .templateUrl("auth/verify")
                .data(user)
                .build();
        try {
            mailUtil.send(meta);
        } catch (MessagingException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage());
        }
    }
    @Override
    public void checkVerify(String email, String verifiedCode) {
        User user  = authMapper.selectByEmailAndVerifiedCode(email,verifiedCode)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Email not found."));
        if (!user.getIsVerified()){
            authMapper.updateIsVerifyStatus(email,verifiedCode);
        }
    }

    @Override
    public AuthDto login(LogInDto logInDto) {
//        call spring security to authenticate user
        Authentication authentication = new UsernamePasswordAuthenticationToken(logInDto.email(),logInDto.password());
        authentication=daoAuthenticationProvider.authenticate(authentication);
//        log.info("User: {}", authentication);
//        logic on basic authorization header;
//        String basicAuthFormat = authentication.getName() + ":" + authentication.getCredentials();
//        String encoding = Base64.getEncoder().encodeToString(basicAuthFormat.getBytes());
//        Base64.getEncoder().encodeToString(basicAuthFormat.getBytes());
//        log.info("basic {}", encoding);
        // create time now
        Instant now = Instant.now();
        // define scope
        String scope =authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .filter(auth->!auth.startsWith("ROLE_"))
                .collect(Collectors.joining(""));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder().
                issuer("self")
                .issuedAt(now)
                .subject(authentication.getName())
                .expiresAt(now.plusSeconds(3600))
                .claim("scope", scope).
                build();
        String accessToken = jwtEncoder.encode(
                JwtEncoderParameters.from(jwtClaimsSet)
        ).getTokenValue();

        return new AuthDto("Bearer",accessToken);
    }
}
