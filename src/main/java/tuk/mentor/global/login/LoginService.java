package tuk.mentor.global.login;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tuk.mentor.global.session.SessionManager;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final SessionManager sessionManager;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager entityManager;

    /*
    * 로그인
    * */
//    @Transactional
//    public LoginResponse login(LoginRequest request, HttpServletRequest httpServletRequest) {
//
//
//    }
//    }
}
