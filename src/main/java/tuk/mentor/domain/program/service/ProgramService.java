package tuk.mentor.domain.program.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.mentor.repository.MentorRepository;
import tuk.mentor.domain.program.dto.request.ProgramRegisterRequest;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.program.repository.ProgramRepository;
import tuk.mentor.domain.week.entity.ProgramWeek;
import tuk.mentor.domain.week.mapper.ProgramWeekMapper;
import tuk.mentor.global.session.LoginInfo;
import tuk.mentor.global.session.SessionManager;
import tuk.mentor.global.util.DateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service
@SessionAttributes("")
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramWeekMapper programWeekMapper;
    private final MentorRepository mentorRepository;
    private final SessionManager sessionManager;
    private final DateUtil dateUtil;

    /*
     * 프로그램 등록
     * */
    @Transactional
    public void registerProgram(ProgramRegisterRequest request, HttpServletRequest httpServletRequest) {
        // [1] 프로그램 기본 정보 등록
        // [1-1] 세션에 등록된 로그인 정보 조회 및 멘토 조회
        LoginInfo loginInfo = (LoginInfo) sessionManager.getSession(httpServletRequest);
        Mentor mentor = mentorRepository.findById(loginInfo.getUserID()).orElseThrow(RuntimeException::new);

//        Mentor mentor = Mentor.builder()
//                .id(1L)
//                .age(2000)
//                .email("wjdalsckd777@naver.com")
//                .college("한국공학대학교")
//                .major(Major.IT_MANAGEMENT)
//                .password("asdf")
//                .introduce("sdfas")
//                .build();

        // [1-2] Program Entity build
        Program program = Program.builder()
                .mentor(mentor)
                .subject(request.getSubject())
                .detail(request.getDetail())
                .programStartDate(dateUtil.convertStringToLocalDate(request.getProgramStartDate()))
                .programFinishDate(dateUtil.convertStringToLocalDate(request.getProgramFinishDate()))
                .recruitStartDate(dateUtil.convertStringToLocalDate(request.getRecruitStartDate()))
                .recruitFinishDate(dateUtil.convertStringToLocalDate(request.getRecruitFinishDate()))
                .build();

        List<ProgramWeek> programWeeks = new ArrayList<>();
        request.getProgramWeeks()
                .forEach(programWeek -> programWeeks.add(
                        ProgramWeek.builder()
                                .program(program)
                                .detail(programWeek.getDetail())
                                .build()
                ));

        program.setProgramWeeks(programWeeks);

        // [1-3] Program 기본 정보 등록
        programRepository.save(program);

    }
}


