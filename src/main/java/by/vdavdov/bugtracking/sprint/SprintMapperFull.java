package by.vdavdov.bugtracking.sprint;

import by.vdavdov.bugtracking.sprint.to.SprintToFull;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import org.mapstruct.Mapper;

@Mapper(config = TimestampMapper.class)
public interface SprintMapperFull extends BaseMapper<Sprint, SprintToFull> {
}
