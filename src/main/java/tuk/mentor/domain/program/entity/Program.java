package tuk.mentor.domain.program.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.week.entity.ProgramWeek;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Mentor mentor;
    private String subject;
    private String detail;
    private LocalDate programStartDate;
    private LocalDate programFinishDate;
    private LocalDate recruitStartDate;
    private LocalDate recruitFinishDate;
    private Integer capacity;
    private String programPlace;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramWeek> programWeeks = new ArrayList<>();
}
