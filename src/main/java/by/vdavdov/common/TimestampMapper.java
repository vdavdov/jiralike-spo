package by.vdavdov.common;

import by.vdavdov.common.model.TimestampEntry;
import by.vdavdov.common.to.CodeTo;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.MappingTarget;

// https://stackoverflow.com/questions/57860451/mapstruct-inheritance-more-than-one-configuration-prototype-is-application
@MapperConfig(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface TimestampMapper<E extends TimestampEntry, T extends CodeTo> {
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "id", ignore = true)
    E updateFromTo(T to, @MappingTarget E entity);
}
