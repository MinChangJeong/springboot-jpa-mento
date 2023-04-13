package tuk.mentor.domain.mentor.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tuk.mentor.domain.mentor.entity.Major;

@Getter
@Setter
@RequiredArgsConstructor
public class MentorRegisterRequest {
    private String name;
    private String email;
    private String password;
    private Integer age;
    private String college;
    private Major major;
    private Integer grade;
    private String introduce;
}
