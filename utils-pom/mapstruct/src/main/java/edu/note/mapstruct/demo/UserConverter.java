package edu.note.mapstruct.demo;

import java.time.LocalDateTime;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import edu.note.mapstruct.demo.entity.Address;
import edu.note.mapstruct.demo.entity.User;
import edu.note.mapstruct.demo.enums.UserType;
import edu.note.mapstruct.demo.infra.UserPO;

/**
 * @author jackylee
 * @date 2025/7/1 17:14
 */
// @Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "createTime", source = "createTime", defaultExpression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(getNow())")
    @Mapping(target = "name", source = "firstName")
    User toEntity(UserPO source);

    @InheritInverseConfiguration
    @Mapping(target = "createTime", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    UserPO toPO(User target);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    default UserType convertUserType(String userType) {
        // 自定义转换逻辑
        if ("ADMIN".equalsIgnoreCase(userType)) {
            return UserType.ADMIN;
        } else if ("USER".equalsIgnoreCase(userType)) {
            return UserType.CUSTOMER;
        } else if ("GUEST".equalsIgnoreCase(userType)) {
            return UserType.GUEST;
        }
        return UserType.GUEST;
    }

    @Named("addressToString")
    default String addressToString(Address address) {
        if (address == null) {
            return null;
        }
        return String.format("%s, %s, %s %s", address.getCity());
    }

}
