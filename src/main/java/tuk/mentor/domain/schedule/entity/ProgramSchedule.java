package tuk.mentor.domain.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuk.mentor.domain.mentee.entity.Mentee;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.program.entity.Program;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProgramSchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id", nullable = true)
    private Mentor mentor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id", nullable = true)
    private Mentee mentee;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "program_id", nullable = false)
    private Program program;
    private String content;
    private LocalDate scheduleStartDate;
    private LocalDate scheduleFinishDate;
    @Transient
    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;
}
