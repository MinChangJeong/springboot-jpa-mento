package tuk.mentor.domain.week.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProgramWeekRegisterRequest {
    private String content;
    private String programWeekStartDate;
    private String programWeekFinishDate;
}
