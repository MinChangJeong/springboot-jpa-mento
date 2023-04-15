package tuk.mentor.domain.program.repository;

import com.querydsl.core.types.QTuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.program.entity.QProgram;

import javax.persistence.TupleElement;
import java.util.List;
import java.util.stream.Collectors;

import static tuk.mentor.domain.program.entity.QProgram.program;
import static tuk.mentor.domain.week.entity.QProgramWeek.programWeek;
import static tuk.mentor.domain.mentor.entity.QMentor.mentor;

@Repository
public class ProgramRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory queryFactory;
    @Autowired
    public ProgramRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Program.class);
        this.queryFactory = jpaQueryFactory;
    }

    public List<Tuple> getProgramList(String keyword) {
        return queryFactory
                .select(program, programWeek)
                .from(program)
                .innerJoin(programWeek)
                .on(program.id.eq(programWeek.program.id))
                .fetch();
    }

}
