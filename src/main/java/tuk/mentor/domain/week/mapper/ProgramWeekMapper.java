package tuk.mentor.domain.week.mapper;

import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tuk.mentor.domain.program.entity.Program;
import tuk.mentor.domain.week.dto.request.ProgramWeekRegisterRequest;
import tuk.mentor.domain.week.entity.ProgramWeek;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramWeekMapper {
}
