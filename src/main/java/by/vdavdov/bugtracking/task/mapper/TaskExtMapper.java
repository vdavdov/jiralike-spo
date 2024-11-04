package by.vdavdov.bugtracking.task.mapper;

import by.vdavdov.bugtracking.project.ProjectMapper;
import by.vdavdov.bugtracking.sprint.SprintMapper;
import by.vdavdov.bugtracking.task.Task;
import by.vdavdov.bugtracking.task.to.TaskToExt;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import by.vdavdov.common.error.DataConflictException;
import by.vdavdov.login.AuthUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = TimestampMapper.class, uses = {SprintMapper.class, ProjectMapper.class})
public interface TaskExtMapper extends BaseMapper<Task, TaskToExt> {

    static long checkProjectBelong(long projectId, Task dbTask) {
        if (projectId != dbTask.getProjectId())
            throw new DataConflictException("Task " + dbTask.id() + " doesn't belong to Project " + projectId);
        return projectId;
    }

    static long checkUserAuthorities(long sprintId, Task dbTask) {
        if (sprintId != dbTask.getSprintId() && !(AuthUser.get().isAdmin() || AuthUser.get().isManager()))
            throw new DataConflictException("Do not have authorities to change task's sprint");
        return sprintId;
    }

    @Override
    TaskToExt toTo(Task task);

    @Override
    @Mapping(target = "projectId", expression = "java(TaskExtMapper.checkProjectBelong(taskToExt.getProjectId(), task))")
    @Mapping(target = "sprintId", expression = "java(TaskExtMapper.checkUserAuthorities(taskToExt.getSprintId(), task))")
    Task updateFromTo(TaskToExt taskToExt, @MappingTarget Task task);
}
