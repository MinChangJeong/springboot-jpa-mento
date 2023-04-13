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
    public MentorRegisterResponse registerMentor(MentorRegisterRequest data, MultipartFile image) throws IOException {

        // [1] Mentor 기본 정보 저장
        Mentor mentor = mentorMapper.toEntityFromRegisterRequest(data);

        // [1-1] 비밀번호 암호화
        mentor.setPassword(passwordEncoder.encode(data.getPassword()));

        // [1-2] GCP Storage profile image url
        String url = s3Manager.upload(image, s3Manager.getDirName());
        mentor.setImgUrl(url);

        // [1-2] 멘토 정보 저장
        entityManager.persist(mentor);
        entityManager.flush();

        MentorRegisterResponse response = MentorRegisterResponse.builder()
                .id(mentor.getId())
                .role(mentor.getRole())
                .build();

        return response;
    }
}
