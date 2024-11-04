package by.vdavdov.bugtracking.task.mapper;

import by.vdavdov.bugtracking.task.Task;
import by.vdavdov.bugtracking.task.to.TaskToFull;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import org.mapstruct.Mapper;

@Mapper(config = TimestampMapper.class)
public interface TaskFullMapper extends BaseMapper<Task, TaskToFull> {

    @Override
    TaskToFull toTo(Task task);
}
