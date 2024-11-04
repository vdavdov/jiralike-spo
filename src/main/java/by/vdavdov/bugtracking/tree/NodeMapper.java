package by.vdavdov.bugtracking.tree;

import by.vdavdov.bugtracking.project.to.ProjectTo;
import by.vdavdov.bugtracking.sprint.to.SprintTo;
import by.vdavdov.bugtracking.task.to.TaskTo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NodeMapper {
    @Mapping(target = "type", expression = "java(ObjectType.PROJECT)")
    NodeTo fromProject(ProjectTo project);

    @Mapping(target = "type", expression = "java(ObjectType.SPRINT)")
    @Mapping(target = "parentId", expression = "java(null)")
    NodeTo fromSprint(SprintTo sprint);

    @Mapping(target = "type", expression = "java(ObjectType.TASK)")
    NodeTo fromTask(TaskTo task);
}
