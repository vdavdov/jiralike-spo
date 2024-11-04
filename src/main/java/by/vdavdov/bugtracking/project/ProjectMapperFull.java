package by.vdavdov.bugtracking.project;

import by.vdavdov.bugtracking.project.to.ProjectToFull;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import org.mapstruct.Mapper;

@Mapper(config = TimestampMapper.class)
public interface ProjectMapperFull extends BaseMapper<Project, ProjectToFull> {
}
