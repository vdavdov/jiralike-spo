package by.vdavdov.bugtracking.sprint;

import by.vdavdov.bugtracking.sprint.to.SprintTo;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import by.vdavdov.common.error.DataConflictException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = TimestampMapper.class)
public interface SprintMapper extends BaseMapper<Sprint, SprintTo> {
    static long checkProjectBelong(long projectId, Sprint dbSprint) {
        if (projectId != dbSprint.getProjectId())
            throw new DataConflictException("Sprint " + dbSprint.id() + " doesn't belong to Project " + projectId);
        return projectId;
    }

    @Override
    @Mapping(target = "projectId", expression = "java(SprintMapper.checkProjectBelong(sprintTo.getProjectId(), sprint))")
    Sprint updateFromTo(SprintTo sprintTo, @MappingTarget Sprint sprint);
}
