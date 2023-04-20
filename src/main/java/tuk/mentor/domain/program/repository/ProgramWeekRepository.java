package tuk.mentor.domain.program.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.entity.ProgramWeek;

@Repository
public interface ProgramWeekRepository extends JpaRepository<ProgramWeek, Long> {
}