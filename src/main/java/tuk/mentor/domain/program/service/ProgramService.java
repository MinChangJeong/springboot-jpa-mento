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
import tuk.mentor.domain.week.mapper.ProgramWeekMapper;
import tuk.mentor.domain.week.repository.ProgramWeekRepository;
import tuk.mentor.global.session.SessionManager;
import tuk.mentor.global.util.DateUtil;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@SessionAttributes("")
@RequiredArgsConstructor
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramWeekRepository programWeekRepository;
    private final ProgramMapper programMapper;
    private final ProgramWeekMapper programWeekMapper;
    private final ProgramRepositorySupport programRepositorySupport;
    private final MentorRepository mentorRepository;
    private final EntityManager entityManager;
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

        /*
         * 문제1. programMapper.toEntity(request, mentor, programWeeks)를 하면 program.id 가 null이 아님.
         * programRepository.save(program); 시 insert전에 select를 먼저 함. 이때 일치하는 program 정보가 있는 탓인지 insert를 안하는 문제가 있음.
         * 문제2. program_week 테이블의 program_id(fk)도 insert되지 않는 문제가 있다.
         *
         * 해결1 : select -> insert문제는 해결 못함
         * 해결2 : program_week 테이블의 program_id(fk)도 insert되지 않는 문제는 saveAll로 해결
         * */
        Program program = programRepository.save(
                Program.builder()
                    .mentor(mentor)
                    .subject(request.getSubject())
                    .build()
        );

        // [1-2] Program Entity map
        Set<ProgramWeek> programWeeks = request.getProgramWeeks().stream().map(programWeek -> ProgramWeek.builder()
                .program(program)
                .content(programWeek.getContent())
                .programWeekStartDate(dateUtil.convertStringToLocalDate(programWeek.getProgramWeekStartDate()))
                .programWeekFinishDate(dateUtil.convertStringToLocalDate(programWeek.getProgramWeekFinishDate()))
                .build()).collect(Collectors.toSet());

        // [1-3] Program & ProgramWeeks 기본 정보 등록
        programWeekRepository.saveAll(programWeeks);

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


