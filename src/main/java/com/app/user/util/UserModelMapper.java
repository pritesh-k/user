package com.app.user.util;

import com.app.user.model.api.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserModelMapper {

    /**
     * Converts a User entity to User DTO.
     *
     * @param user The User entity to be converted.
     * @return The corresponding User DTO.
     */
    User userEntityToDto(com.app.user.model.data.User user);

    /**
     * Converts a User DTO to User entity.
     *
     * @param user The User DTO to be converted.
     * @return The corresponding User entity.
     */
    com.app.user.model.data.User userDtoToEntity(User user);
}
