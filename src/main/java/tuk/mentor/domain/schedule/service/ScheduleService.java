package tuk.mentor.domain.schedule.service;

import org.springframework.stereotype.Service;
import tuk.mentor.domain.schedule.dto.request.ScheduleRegisterRequest;
import tuk.mentor.global.login.LoginInfo;

@Service
public interface ScheduleService {
    void registerSchedule(ScheduleRegisterRequest request, LoginInfo loginInfo);
}
