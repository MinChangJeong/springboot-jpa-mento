package tuk.mentor.domain.program.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;
import tuk.mentor.domain.program.entity.Program;

@Repository
public class ProgramRepositorySupport extends QuerydslRepositorySupport {

    private final JPAQueryFactory jpaQueryFactory;

    @Autowired
    public ProgramRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        super(Program.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }


}
