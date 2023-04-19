package tuk.mentor.domain.program.repository;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.entity.Program;

import java.util.List;

import static tuk.mentor.domain.program.entity.QProgram.program;
import static tuk.mentor.domain.program.entity.QProgramParticipation.programParticipation;
import static tuk.mentor.domain.mentor.entity.QMentor.mentor;
@Repository
public class ProgramRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    @Autowired
    public ProgramRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Program.class);
        this.queryFactory = jpaQueryFactory;
    }

//    public List<ProgramListResponse> getProgramList(String keyword) {
//
//        return queryFactory.selectFrom(program)
//                .leftJoin(program.programWeeks, programWeek)
//                .distinct()
//                .transform(
//                        groupBy(program.id).list(
//                                Projections.constructor(ProgramListResponse.class,
//                                        program.id,
//                                        program.subject,
//                                        program.detail,
//                                        program.programStartDate,
//                                        program.programFinishDate,
//                                        program.capacity,
//                                        program.programPlace,
//                                        list(Projections.constructor(ProgramWeekResponse.class,
//                                                programWeek.id,
//                                                programWeek.detail))
//                                )
//                        )
//                );
//    }
    public List<Tuple> getProgramList(String keyword) {

        return queryFactory.select(
                        mentor.name,
                        mentor.college,
                        mentor.major,
                        mentor.lesson,
                        program.id,
                        program.programStartDate,
                        program.programFinishDate,
                        program.subject,
                        program.detail,
                        program.capacity
//                        queryFactory
//                                .select(
//                                        programParticipation.count().as("participant")
//                                )
//                                .from(program)
//                                .where(program.id.eq(programParticipation.program.id))
                )
                .from(program)
                .innerJoin(program.mentor, mentor)
                .fetch();
    }
}
