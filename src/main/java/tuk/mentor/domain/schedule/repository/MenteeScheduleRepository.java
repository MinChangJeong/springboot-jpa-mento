package tuk.mentor.domain.schedule.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.schedule.entity.MenteeSchedule;

@Repository
public interface MenteeScheduleRepository extends JpaRepository<MenteeSchedule, Long> {

}