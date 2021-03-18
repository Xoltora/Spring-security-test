package uz.pdp.springbootsecuritytestdemo.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springbootsecuritytestdemo.entity.User;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsernameOrEmailOrPhoneNumber(String username, String email, String phoneNumber);
}
