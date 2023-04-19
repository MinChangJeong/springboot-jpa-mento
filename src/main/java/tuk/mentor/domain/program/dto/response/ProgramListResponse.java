package tuk.mentor.domain.program.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.week.dto.response.ProgramWeekResponse;
import tuk.mentor.domain.week.entity.ProgramWeek;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProgramListResponse {
    private Long id;
    private String subject;
    private String detail;
    private LocalDate programStartDate;
    private LocalDate programFinishDate;
    private Integer capacity;
    private String programPlace;
    private List<ProgramWeekResponse> programWeekResponses;

    public ProgramListResponse(Program program){
        Set<ProgramWeek> programWeeks = program.getProgramWeeks();
        this.programWeekResponses = programWeeks.stream()
                .map(ProgramWeek::getProgram)
                .map(programWeek -> new ProgramWeekResponse(programWeek.getId(), programWeek.getDetail()))
                .collect(Collectors.toList());

        this.id = program.getId();
        this.subject = program.getSubject();
        this.detail = program.getDetail();
        this.programStartDate = program.getProgramStartDate();
        this.programFinishDate = program.getProgramFinishDate();
        this.capacity = program.getCapacity();
        this.programPlace = program.getProgramPlace();
    }
}
