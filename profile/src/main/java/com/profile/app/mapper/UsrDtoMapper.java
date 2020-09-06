package com.profile.app.mapper;

import com.profile.app.domain.Profile;
import com.profile.app.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsrDtoMapper {

    UsrDtoMapper INSTANCE = Mappers.getMapper( UsrDtoMapper.class );

    UserDto usrToUserDto(Profile profile);

    Profile userDtoToUsr(UserDto userDto);
}
