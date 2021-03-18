package uz.pdp.springbootsecuritytestdemo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.springbootsecuritytestdemo.entity.enums.RoleName;

import javax.persistence.Column;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private UUID id;
    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
    private String password;
    private List<RoleName> roleNames;
}

/*
"fullName" : "admin2",
    "username" : "admin2",
    "phoneNumber" : "+333",
    "email" : "admin2@gmail.com",
    "password" : "admin2",
    "roleNames" : [
        "ROLE_ADMIN",
        "ROLE_MODERATOR"
    ]
 */
