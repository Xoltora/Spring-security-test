package uz.pdp.springbootsecuritytestdemo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.springbootsecuritytestdemo.entity.Book;
import uz.pdp.springbootsecuritytestdemo.entity.Role;
import uz.pdp.springbootsecuritytestdemo.entity.User;
import uz.pdp.springbootsecuritytestdemo.entity.enums.RoleName;
import uz.pdp.springbootsecuritytestdemo.reposiroty.BookRepository;
import uz.pdp.springbootsecuritytestdemo.reposiroty.RoleRepository;
import uz.pdp.springbootsecuritytestdemo.reposiroty.UserRepository;

import java.util.Collections;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.datasource.initialization-mode}")
    private String mode;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always")){
            Role superAdmin = roleRepository.save(new Role(RoleName.ROLE_SUPER_ADMIN));
            Role admin = roleRepository.save(new Role(RoleName.ROLE_ADMIN));
            Role moderator = roleRepository.save(new Role(RoleName.ROLE_MODERATOR));
            Role client = roleRepository.save(new Role(RoleName.ROLE_CLIENT));

            userRepository.save(new User("superAdmin", passwordEncoder.encode("superAdmin"),
                    "superAdmin", "+999"
                    ,"superAdminEmail@gmail.com", Collections.singletonList(superAdmin)));

            userRepository.save(new User("admin", passwordEncoder.encode("admin"),
                    "admin", "+888"
                    ,"adminEmail@gmail.com", Collections.singletonList(admin)));

            userRepository.save(new User("moder", passwordEncoder.encode("moder"),
                    "moder", "+777"
                    ,"moderEmail@gmail.com", Collections.singletonList(moderator)));

            userRepository.save(new User("client", passwordEncoder.encode("client"),
                    "client", "+666"
                    ,"clientEmail@gmail.com", Collections.singletonList(client)));

            bookRepository.save(new Book("O'tgan kunlar", "Abdulla Qodiriy"));

        }
    }
}
