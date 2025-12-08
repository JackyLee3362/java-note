package edu.note.mapstruct.lifecycle;

import java.time.LocalDateTime;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

/**
 * @author jackylee
 * @date 2025/7/1 17:14
 */
@Mapper
public interface LifeCycleConverter {

    LifeCycleConverter INSTANCE = Mappers.getMapper(LifeCycleConverter.class);

    @Mapping(target = "userId", source = "id")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "createTime", ignore = true)
    Target toTarget(Source source);

    @InheritInverseConfiguration
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    Source toSource(Target target);

    default LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    @BeforeMapping
    default void setCreateTime(@MappingTarget Target target) {
        target.setCreateTime(getNow());
    }

    @AfterMapping
    default void setName(Source source, @MappingTarget Target target) {
        target.setName(source.getFirstName() + source.getLastName());
    }

}
