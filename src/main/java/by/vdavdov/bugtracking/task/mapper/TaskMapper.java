package by.vdavdov.bugtracking.task.mapper;

import by.vdavdov.bugtracking.task.Task;
import by.vdavdov.bugtracking.task.to.TaskTo;
import by.vdavdov.common.BaseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper extends BaseMapper<Task, TaskTo> {

    @Override
    TaskTo toTo(Task task);
}
