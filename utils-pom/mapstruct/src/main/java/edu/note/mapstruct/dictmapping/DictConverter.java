package edu.note.mapstruct.dictmapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import edu.note.mapstruct.dictmapping.MappingUtil.Host;
import edu.note.mapstruct.dictmapping.MappingUtil.Port;

/**
 * @author jackylee
 * @date 2025/7/1 17:14
 */
@Mapper(uses = { MappingUtil.class })
public interface DictConverter {

    DictConverter INSTANCE = Mappers.getMapper(DictConverter.class);

    @Mapping(target = "host", source = "map", qualifiedBy = Host.class)
    @Mapping(target = "port", source = "map", qualifiedBy = Port.class)
    Target toTarget(Source userPO);

}
