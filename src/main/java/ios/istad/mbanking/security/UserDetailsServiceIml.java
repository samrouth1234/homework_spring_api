package ios.istad.mbanking.security;

import ios.istad.mbanking.api.user.Authority;
import ios.istad.mbanking.api.user.Role;
import ios.istad.mbanking.api.user.User;
import ios.istad.mbanking.auth.AuthMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceIml implements UserDetailsService {

    private final AuthMapper authMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUserName : {}",username);
        User user = authMapper.loadUserByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User is not valid."));

        log.info("User :{}",user);
        for (Role role : user.getRoles()){
            for (Authority authority :role.getAuthorities()){
                System.out.println(authority.getName());
            }
        }

        CustomUserDetails customUserDetails =new CustomUserDetails();
        customUserDetails.setUser(user);

        log.info("Custom User Details :{}",customUserDetails);
        log.info("Custom User Details :{}",customUserDetails.getAuthorities());
        return customUserDetails;
    }

}
