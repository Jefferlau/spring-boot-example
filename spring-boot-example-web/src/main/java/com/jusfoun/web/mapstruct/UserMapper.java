package com.jusfoun.web.mapstruct;

import com.jusfoun.model.User;
import com.jusfoun.web.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Create on 2017-06-26.
 *
 * @author Jeffer Lau <jefferlzu@gmail.com>
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);

    @Mappings({
            @Mapping(target = "status", ignore = true),
            @Mapping(target = "createTime", ignore = true),
            @Mapping(target = "updateTime", ignore = true),
            @Mapping(target = "avatar", ignore = true)
    })
    User toUser(UserDto userDto);

    List<UserDto> toUserDtoList(List<User> userList);

    List<User> toUserList(List<UserDto> userDtoList);
}
