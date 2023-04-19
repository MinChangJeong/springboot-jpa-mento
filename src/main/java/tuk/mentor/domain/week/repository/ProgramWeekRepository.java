package tuk.mentor.domain.week.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.week.entity.ProgramWeek;

@Repository
public interface ProgramWeekRepository extends JpaRepository<ProgramWeek, Long> {
}