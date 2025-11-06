package edu.note.mapstruct;

import java.time.LocalDateTime;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author jackylee
 * @date 2025/7/1 17:14
 */
@Mapper
public interface BasicConverter {

    BasicConverter INSTANCE = Mappers.getMapper(BasicConverter.class);

    UserDTO toUserDTO(User userPO);

    // 可以抱一层 @Mappers ，但是推荐多个 @Mapping
    @Mapping(target = "id", source = "userId")
    @Mapping(target = "name", source = "firstName")
    // @Mapping(target = "device", defaultValue = "website")
    // @Mapping(target = "createTime", defaultExpression = "java(getNow())")
    User toUser(UserDTO userDTO);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }

}
