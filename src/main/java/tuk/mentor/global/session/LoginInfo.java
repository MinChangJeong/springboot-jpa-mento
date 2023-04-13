package tuk.mentor.global.session;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@Component
@RequiredArgsConstructor
public class LoginInfo {
    private Long userID;
    private Boolean loginStatus;
}
