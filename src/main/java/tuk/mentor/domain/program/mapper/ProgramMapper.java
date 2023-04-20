package tuk.mentor.domain.program.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import tuk.mentor.domain.mentor.entity.Mentor;
import tuk.mentor.domain.program.dto.request.ProgramRegisterRequest;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.week.entity.ProgramWeek;

import java.util.Set;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProgramMapper {
}
