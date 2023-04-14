package tuk.mentor.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.schedule.entity.ProgramSchedule;

@Repository
public interface ProgramScheduleRepository extends JpaRepository<ProgramSchedule, Long> {

}