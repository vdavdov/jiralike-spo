package by.vdavdov.bugtracking;

import by.vdavdov.bugtracking.attachment.Attachment;
import by.vdavdov.bugtracking.attachment.AttachmentMapper;
import by.vdavdov.bugtracking.attachment.AttachmentRepository;
import by.vdavdov.bugtracking.attachment.to.AttachmentTo;
import by.vdavdov.bugtracking.project.Project;
import by.vdavdov.bugtracking.project.ProjectMapper;
import by.vdavdov.bugtracking.project.ProjectRepository;
import by.vdavdov.bugtracking.project.to.ProjectTo;
import by.vdavdov.bugtracking.sprint.Sprint;
import by.vdavdov.bugtracking.sprint.SprintMapper;
import by.vdavdov.bugtracking.sprint.SprintRepository;
import by.vdavdov.bugtracking.sprint.to.SprintTo;
import by.vdavdov.bugtracking.task.Activity;
import by.vdavdov.bugtracking.task.ActivityRepository;
import by.vdavdov.bugtracking.task.Task;
import by.vdavdov.bugtracking.task.TaskRepository;
import by.vdavdov.bugtracking.task.mapper.ActivityMapper;
import by.vdavdov.bugtracking.task.mapper.TaskExtMapper;
import by.vdavdov.bugtracking.task.mapper.TaskFullMapper;
import by.vdavdov.bugtracking.task.mapper.TaskMapper;
import by.vdavdov.bugtracking.task.to.ActivityTo;
import by.vdavdov.bugtracking.task.to.TaskTo;
import by.vdavdov.bugtracking.task.to.TaskToExt;
import by.vdavdov.bugtracking.task.to.TaskToFull;
import by.vdavdov.common.BaseHandler;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.BaseRepository;
import by.vdavdov.common.HasId;
import by.vdavdov.common.to.BaseTo;
import by.vdavdov.login.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Handlers {
    @Component
    public static class ProjectHandler extends UserBelongHandler<Project, ProjectTo, ProjectRepository, ProjectMapper> {
        public ProjectHandler(ProjectRepository repository, ProjectMapper mapper) {
            super(repository, mapper);
        }
    }

    @Component
    public static class SprintHandler extends UserBelongHandler<Sprint, SprintTo, SprintRepository, SprintMapper> {
        public SprintHandler(SprintRepository repository, SprintMapper mapper) {
            super(repository, mapper, null, (sprint, dbSprint) -> {  // link spring to other project not allowed
                SprintMapper.checkProjectBelong(sprint.getProjectId(), dbSprint);
                sprint.setStartpoint(dbSprint.getStartpoint());
                sprint.setEndpoint(dbSprint.getEndpoint());
                return sprint;
            });
        }
    }

    @Component
    public static class TaskHandler extends UserBelongHandler<Task, TaskTo, TaskRepository, TaskMapper> {
        public TaskHandler(TaskRepository repository, TaskMapper mapper) {
            super(repository, mapper);
        }
    }

    @Component
    public static class TaskFullHandler extends UserBelongHandler<Task, TaskToFull, TaskRepository, TaskFullMapper> {
        public TaskFullHandler(TaskRepository repository, TaskFullMapper mapper) {
            super(repository, mapper);
        }
    }

    @Component
    public static class TaskExtHandler extends UserBelongHandler<Task, TaskToExt, TaskRepository, TaskExtMapper> {
        public TaskExtHandler(TaskRepository repository, TaskExtMapper mapper) {
            super(repository, mapper);
        }
    }

    @Component
    public static class ActivityHandler extends BaseHandler<Activity, ActivityTo, ActivityRepository, ActivityMapper> {
        public ActivityHandler(ActivityRepository repository, ActivityMapper mapper) {
            super(repository, mapper);
        }
    }

    @Component
    public static class AttachmentHandler extends BaseHandler<Attachment, AttachmentTo, AttachmentRepository, AttachmentMapper> {
        public AttachmentHandler(AttachmentRepository repository, AttachmentMapper mapper) {
            super(repository, mapper);
        }
    }

    @Slf4j
    public static class UserBelongHandler<E extends HasId, T extends BaseTo, R extends BaseRepository<E>, M extends BaseMapper<E, T>> extends BaseHandler<E, T, R, M> {
        @Autowired
        private UserBelongRepository belongRepository;

        public UserBelongHandler(R repository, M mapper) {
            super(repository, mapper);
        }

        public UserBelongHandler(R repository, M mapper, Function<E, E> prepareForSave, BiFunction<E, E, E> prepareForUpdate) {
            super(repository, mapper, prepareForSave, prepareForUpdate);
        }

        public List<UserBelong> getAllBelongs(long objectId) {
            return belongRepository.findAll(objectId);
        }

        @Transactional
        public E createWithBelong(T to, ObjectType type, String userTypeCode) {
            E created = super.createFromTo(to);
            createUserBelong(created.id(), type, AuthUser.authId(), userTypeCode);
            return created;
        }

        @Transactional
        public void createUserBelong(long id, ObjectType type, long userId, String userTypeCode) {
            if (belongRepository.findActiveAssignment(id, type, userId, userTypeCode).isEmpty()) {
                UserBelong belong = new UserBelong(id, type, userId, userTypeCode);
                belongRepository.save(belong);
            }
        }
    }
}
