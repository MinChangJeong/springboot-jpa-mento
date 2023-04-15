package tuk.mentor.global.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import tuk.mentor.domain.mentee.entity.Mentee;
import tuk.mentor.domain.mentee.repository.MenteeRepository;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.mentor.repository.MentorRepository;
import tuk.mentor.global.session.SessionManager;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class LoginManager {

    private final SessionManager sessionManager;
    private MentorRepository mentorRepository;
    private MenteeRepository menteeRepository;
    public <T> T getLoginEntity(HttpServletRequest httpServletRequest) {
        ObjectMapper mapper = new ObjectMapper();

        LoginInfo loginInfo = (LoginInfo) sessionManager.getSession(httpServletRequest);
        switch (loginInfo.getRole()) {
            case MENTOR -> {
                Mentor mentor = mentorRepository.findById(loginInfo.getUserID()).
                        orElseThrow(RuntimeException::new);
                return (T) mentor;
            }
            case MENTEE -> {
                Mentee mentee = menteeRepository.findById(loginInfo.getUserID()).
                        orElseThrow(RuntimeException::new);
                return (T) mentee;
            }
            default -> {
                throw new EntityExistsException();
            }
        }
    }
}
