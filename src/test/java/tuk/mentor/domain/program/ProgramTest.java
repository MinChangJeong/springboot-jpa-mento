package tuk.mentor.domain.program;

import com.querydsl.core.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tuk.mentor.domain.mentor.entity.Major;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.program.repository.ProgramRepositorySupport;
import tuk.mentor.domain.week.entity.ProgramWeek;
import tuk.mentor.support.resolver.EntityManagerResolver;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(EntityManagerResolver.class)
@Transactional
public class ProgramTest {

    @Autowired
    private ProgramRepositorySupport programRepositorySupport;

    @Autowired
    private EntityManager entityManager;

    @Test
    void getProgramListTest() {

        Mentor mentor = Mentor.builder()
                .id(1L)
                .age(2000)
                .email("wjdalsckd777@naver.com")
                .college("한국공학대학교")
                .major(Major.IT_MANAGEMENT)
                .password("asdf")
                .introduce("sdfas")
                .build();

        entityManager.persist(mentor);

        Program program1 = Program.builder()
                .mentor(mentor)
                .subject("program1")
                .detail("detail1")
                .programStartDate(LocalDate.now())
                .programFinishDate(LocalDate.now())
                .recruitStartDate(LocalDate.now())
                .recruitFinishDate(LocalDate.now())
                .programPlace("학교")
                .capacity(10)
                .build();

        Program program2 = Program.builder()
                .mentor(mentor)
                .subject("program2")
                .detail("detail2")
                .programStartDate(LocalDate.now())
                .programFinishDate(LocalDate.now())
                .recruitStartDate(LocalDate.now())
                .recruitFinishDate(LocalDate.now())
                .programPlace("학교")
                .capacity(10)
                .build();

        entityManager.persist(program1);
        entityManager.persist(program2);

        ProgramWeek programWeek1 = ProgramWeek.builder()
                .program(program1)
                .detail("program-week-test")
                .build();

        ProgramWeek programWeek2 = ProgramWeek.builder()
                .program(program2)
                .detail("program-week-test")
                .build();

        entityManager.persist(program1);
        entityManager.persist(program2);

        // when
        List<Tuple> result = programRepositorySupport.getProgramList("");

        // then
        assertThat(result).hasSize(2);

    }
}
