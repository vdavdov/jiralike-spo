package by.vdavdov.ref.internal;

import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import by.vdavdov.ref.RefTo;
import org.mapstruct.Mapper;

@Mapper(config = TimestampMapper.class)
public interface ReferenceMapper extends BaseMapper<Reference, RefTo> {
}
