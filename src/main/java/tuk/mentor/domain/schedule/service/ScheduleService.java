package tuk.mentor.domain.schedule.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;
import tuk.mentor.domain.mentee.entity.Mentee;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.program.repository.ProgramRepository;
import tuk.mentor.domain.schedule.dto.request.ScheduleRegisterRequest;
import tuk.mentor.domain.schedule.entity.Schedule;
import tuk.mentor.domain.schedule.repository.ScheduleRepository;
import tuk.mentor.global.login.LoginManager;
import tuk.mentor.global.session.SessionManager;
import tuk.mentor.global.util.DateUtil;

import javax.servlet.http.HttpServletRequest;

@Service
@SessionAttributes("")
@RequiredArgsConstructor
public class ScheduleService {
    private final ProgramRepository programRepository;
    private final ScheduleRepository scheduleRepository;
    private final SessionManager sessionManager;
    private final LoginManager loginManager;
    private final DateUtil dateUtil;

    /*
     * 프로그램 일정 등록
     * */
    @Transactional
    public void scheduleService(ScheduleRegisterRequest request, HttpServletRequest httpServletRequest) {
        // [1] 프로그램 일정 기본 정보 등록
        // [1-1] 세션에 등록된 로그인 정보 조회 및 멘토 조회
        Object user = loginManager.getLoginEntity(httpServletRequest);
//        Mentor mentor = Mentor.builder()
//                .id(1L)
//                .age(2000)
//                .email("wjdalsckd777@naver.com")
//                .college("한국공학대학교")
//                .major(Major.IT_MANAGEMENT)
//                .password("asdf")
//                .introduce("sdfas")
//                .build();

        // [1-2] Schedule Entity build
        Schedule schedule = Schedule.builder()
                .mentor(user.getClass().equals(Mentor.class) ? (Mentor) user : null)
                .mentee(user.getClass().equals(Mentee.class) ? (Mentee) user : null)
                .content(request.getContent())
                .scheduleStartDate(dateUtil.convertStringToLocalDate(request.getScheduleStartDate()))
                .scheduleStartDate(dateUtil.convertStringToLocalDate(request.getScheduleStartDate()))
                .build();

        // [1-3] Program 기본 정보 등록
        scheduleRepository.save(schedule);
    }
}


