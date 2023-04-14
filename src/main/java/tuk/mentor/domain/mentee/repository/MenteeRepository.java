package tuk.mentor.domain.mentee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.mentee.entity.Mentee;
import tuk.mentor.domain.mentor.entity.Mentor;

@Repository
public interface MenteeRepository extends JpaRepository<Mentee, Long> {
}