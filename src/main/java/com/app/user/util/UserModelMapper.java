package com.app.user.util;

import com.app.user.model.api.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelMapper {

    User userEntityToDto(com.app.user.model.data.User user);
    com.app.user.model.data.User userDtoToEntity(User user);
}
