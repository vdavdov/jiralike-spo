package by.vdavdov.bugtracking.project;

import by.vdavdov.bugtracking.project.to.ProjectTo;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import org.mapstruct.Mapper;

@Mapper(config = TimestampMapper.class)
public interface ProjectMapper extends BaseMapper<Project, ProjectTo> {
}
