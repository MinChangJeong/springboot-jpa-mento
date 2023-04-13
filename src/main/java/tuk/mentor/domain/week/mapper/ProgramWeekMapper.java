package tuk.mentor.domain.week.mapper;

import org.mapstruct.Mapper;
import tuk.mentor.domain.week.dto.request.ProgramWeekRegisterRequest;
import tuk.mentor.domain.week.entity.ProgramWeek;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProgramWeekMapper {
    List<ProgramWeek> toEntityFromRegisterRequest(List<ProgramWeekRegisterRequest> request);
}
