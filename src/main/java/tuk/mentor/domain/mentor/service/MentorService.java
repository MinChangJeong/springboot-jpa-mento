package tuk.mentor.domain.mentor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tuk.mentor.domain.mentor.dto.request.MentorRegisterRequest;
import tuk.mentor.domain.mentor.dto.response.MentorRegisterResponse;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.mentor.mapper.MentorMapper;
import tuk.mentor.domain.mentor.repository.MentorRepository;
import tuk.mentor.global.s3.manager.S3Manager;
import tuk.mentor.global.util.StringUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final MentorRepository mentorRepository;
    private final MentorMapper mentorMapper;
    @PersistenceContext
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;
    private final S3Manager s3Manager;
    /*
    *  멘토 등록
    * */
    @Transactional
    public MentorRegisterResponse registerMentor(MentorRegisterRequest request, MultipartFile image) throws IOException {

        StringUtil stringutil = new StringUtil();

        // [1] Mentor 기본 정보 저장
        Mentor mentor = mentorMapper.toEntityFromRegisterRequest(request);

        // [1-1] 비밀번호 암호화
        mentor.setPassword(passwordEncoder.encode(request.getPassword()));

        // [1-2] GCP Storage profile image url
        String url = s3Manager.upload(image, s3Manager.getDirName());
        mentor.setImgUrl(url);

        // [1-2] 멘토 정보 저장

        Long id = mentorRepository
                        .save(mentor)
                        .getId();

        System.out.println(stringutil.toString(mentor));

        MentorRegisterResponse response = MentorRegisterResponse.builder()
                .id(id)
                .role(mentor.getRole())
                .build();

        System.out.println(response.getId());
        System.out.println(response.getRole());

        return response;
    }
}
