package tuk.mentor.domain.program.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.mentor.repository.MentorRepository;
import tuk.mentor.domain.program.dto.request.ProgramRegisterRequest;
import tuk.mentor.domain.program.dto.response.ProgramListResponse;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.program.mapper.ProgramMapper;
import tuk.mentor.domain.program.repository.ProgramRepository;
import tuk.mentor.domain.program.repository.ProgramRepositorySupport;
import tuk.mentor.domain.week.entity.ProgramWeek;
import tuk.mentor.global.session.SessionManager;
import tuk.mentor.global.util.DateUtil;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Service
@SessionAttributes("")
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;
    private final ProgramRepositorySupport programRepositorySupport;
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
//        LoginInfo loginInfo = (LoginInfo) sessionManager.getSession(httpServletRequest);
//        Mentor mentor = mentorRepository.findById(loginInfo.getUserID()).orElseThrow(RuntimeException::new);

        Mentor mentor = mentorRepository.findById(1L).orElseThrow(EntityExistsException::new);

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

        Set<ProgramWeek> programWeeks = new HashSet<>();
        request.getProgramWeeks()
                .forEach(programWeek -> programWeeks.add(
                        ProgramWeek.builder()
                                .program(program)
                                .detail(programWeek.getDetail())
                                .programWeekStartDate(dateUtil.convertStringToLocalDate(programWeek.getProgramWeekStartDate()))
                                .programWeekFinishDate(dateUtil.convertStringToLocalDate(programWeek.getProgramWeekFinishDate()))
                                .build()
                ));

        program.setProgramWeeks(programWeeks);

        // [1-3] Program 기본 정보 등록
        programRepository.save(program);
    }

    /*
     * 프로그램 목록 조회
     * */
    public ProgramListResponse getProgramList(String keyword) {
        ProgramListResponse response = new ProgramListResponse();

        System.out.println(programRepositorySupport.getProgramList(keyword));

        return response;
    }
}


