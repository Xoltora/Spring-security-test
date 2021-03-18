package uz.pdp.springbootsecuritytestdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.springbootsecuritytestdemo.config.SecurityConfig;
import uz.pdp.springbootsecuritytestdemo.entity.User;
import uz.pdp.springbootsecuritytestdemo.payload.ApiResponse;
import uz.pdp.springbootsecuritytestdemo.payload.UserDto;
import uz.pdp.springbootsecuritytestdemo.reposiroty.RoleRepository;
import uz.pdp.springbootsecuritytestdemo.reposiroty.UserRepository;



@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    SecurityConfig securityConfig;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByUsernameOrEmailOrPhoneNumber(login, login, login);
    }

    public ApiResponse register(UserDto dto) {
        try {
            userRepository.save(new User(dto.getFullName(), securityConfig.passwordEncoder().encode(dto.getPassword()), dto.getUsername(),
                    dto.getPhoneNumber(), dto.getEmail(), roleRepository.findAllByRoleNameIn(dto.getRoleNames())));
            return new ApiResponse("Saved", true);
        }catch (Exception e){
            e.printStackTrace();
            return new ApiResponse("Error", false);
        }
    }
}
