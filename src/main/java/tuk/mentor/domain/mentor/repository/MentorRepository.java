package tuk.mentor.domain.mentor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.mentor.entity.Mentor;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Long> {
}