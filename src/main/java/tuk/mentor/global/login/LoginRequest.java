package tuk.mentor.global.login;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tuk.mentor.domain.mentor.entity.Role;

import javax.validation.constraints.Email;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginRequest {
    @Email
    private String email;
    private String password;
    private Role role;
}
