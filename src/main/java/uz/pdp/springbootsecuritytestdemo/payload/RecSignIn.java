package uz.pdp.springbootsecuritytestdemo.payload;

import lombok.Data;

@Data
public class RecSignIn {
    private String login;
    private String password;
}
