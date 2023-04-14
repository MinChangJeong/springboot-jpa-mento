package tuk.mentor.global.session;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tuk.mentor.domain.mentor.entity.Role;

@Data
@Component
@RequiredArgsConstructor
public class LoginInfo {
    private Long userID;
    private Boolean loginStatus;
    private Role role;
}
