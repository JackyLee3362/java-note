package edu.note.mapstruct.clone;

import org.mapstruct.Mapper;
import org.mapstruct.control.DeepClone;
import org.mapstruct.factory.Mappers;

/**
 * @author jackylee
 * @date 2025-12-08 15:51
 */
@Mapper(mappingControl = DeepClone.class)
public interface CloneConverter {

    CloneConverter INSTANCE = Mappers.getMapper(CloneConverter.class);

    User clone(User source);

}
