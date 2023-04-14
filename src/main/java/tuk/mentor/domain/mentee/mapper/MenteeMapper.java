package tuk.mentor.domain.mentee.mapper;

import org.mapstruct.Mapper;
import tuk.mentor.domain.mentee.dto.request.MenteeRegisterRequest;
import tuk.mentor.domain.mentee.entity.Mentee;
import tuk.mentor.domain.mentor.dto.request.MentorRegisterRequest;

@Mapper(componentModel = "spring")
public interface MenteeMapper {
    Mentee toEntityFromRegisterRequest(MenteeRegisterRequest request);
}
