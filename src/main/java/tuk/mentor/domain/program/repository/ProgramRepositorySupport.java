package tuk.mentor.domain.program.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.dto.response.ProgramListResponse;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.week.dto.response.ProgramWeekResponse;

import java.util.List;

import static com.querydsl.core.group.GroupBy.groupBy;
import static com.querydsl.core.types.Projections.list;
import static tuk.mentor.domain.program.entity.QProgram.program;
import static tuk.mentor.domain.week.entity.QProgramWeek.programWeek;

@Repository
public class ProgramRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    @Autowired
    public ProgramRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Program.class);
        this.queryFactory = jpaQueryFactory;
    }

    public List<ProgramListResponse> getProgramList(String keyword) {

        return queryFactory.selectFrom(program)
                .leftJoin(program.programWeeks, programWeek)
                .distinct()
                .transform(
                        groupBy(program.id).list(
                                Projections.constructor(ProgramListResponse.class,
                                        program.id,
                                        program.subject,
                                        program.detail,
                                        program.programStartDate,
                                        program.programFinishDate,
                                        program.capacity,
                                        program.programPlace,
                                        list(Projections.constructor(ProgramWeekResponse.class,
                                                programWeek.id,
                                                programWeek.detail))
                                )
                        )
                );
    }

}
