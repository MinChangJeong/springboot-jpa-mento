package tuk.mentor.domain.program.dto.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor
public class ProgramListResponse {
    private Long id;
    private String subject;
    private String detail;
    private LocalDate programStartDate;
    private LocalDate programFinishDate;
    private Integer capacity;
    private String programPlace;
}
