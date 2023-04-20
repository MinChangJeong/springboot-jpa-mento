package tuk.mentor.domain.program.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.dto.response.ProgramListResponse;
import tuk.mentor.domain.program.dto.response.QProgramListResponse;
import tuk.mentor.domain.program.entity.Program;

import java.util.List;

import static tuk.mentor.domain.program.entity.QProgram.program;
import static tuk.mentor.domain.program.entity.QProgramParticipation.programParticipation;
import static tuk.mentor.domain.mentor.entity.QMentor.mentor;


@Repository
@RequiredArgsConstructor
public class ProgramQueryRepositoryImpl implements ProgramQueryRepository {
    private final JPAQueryFactory queryFactory;

    public List<ProgramListResponse> getProgramList(String keyword) {

        return queryFactory
                .select(new QProgramListResponse(
                        mentor.name,
                        mentor.college,
                        mentor.major,
                        mentor.lesson,
                        program.id,
                        program.subject,
                        program.detail,
                        program.programStartDate,
                        program.programFinishDate,
                        program.capacity,
                        program.programPlace,
                        queryFactory
                                .select(programParticipation.count().as("participant"))
                                .from(programParticipation)
                                .where(programParticipation.program.id.eq(program.id))
                ))
                .from(program)
                .join(program.mentor, mentor)
                .on(program.mentor.id.eq(mentor.id))
                .fetch();
    }
}
