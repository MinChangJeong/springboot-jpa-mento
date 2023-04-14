package tuk.mentor.domain.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import tuk.mentor.domain.schedule.entity.ScheduleType;

@Getter
@Setter
@RequiredArgsConstructor
public class ProgramScheduleRegisterRequest {
    private Long programId;
    private String scheduleStartDate;
    private String scheduleFinishDate;
    private ScheduleType scheduleType;
    private String content;
}
