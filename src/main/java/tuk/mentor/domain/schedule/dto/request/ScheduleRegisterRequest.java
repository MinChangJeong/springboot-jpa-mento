package tuk.mentor.domain.schedule.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ScheduleRegisterRequest {
    private Long programId;
    private String scheduleStartDate;
    private String scheduleFinishDate;
    private String content;
}
