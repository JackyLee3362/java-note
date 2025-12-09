package edu.note.mapstruct.fieldmapping;

import java.time.LocalDateTime;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.note.mapstruct.fieldmapping.Target.UserType;

/**
 * @author jackylee
 * @date 2025/7/1 17:14
 */
@Mapper
public interface FieldConverter {

    FieldConverter INSTANCE = Mappers.getMapper(FieldConverter.class);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "name", source = "firstName")
    @Mapping(target = "createTime", source = "createTime", defaultExpression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updateTime", expression = "java(getNow())")
    Target toTarget(Source source);

    @InheritInverseConfiguration
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    Source toSource(Target target);

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

}
