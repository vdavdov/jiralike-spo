package by.vdavdov.bugtracking.tree;


import by.vdavdov.bugtracking.project.ProjectMapper;
import by.vdavdov.bugtracking.project.ProjectRepository;
import by.vdavdov.bugtracking.sprint.SprintMapper;
import by.vdavdov.bugtracking.sprint.SprintRepository;
import by.vdavdov.bugtracking.sprint.to.SprintTo;
import by.vdavdov.bugtracking.task.TaskRepository;
import by.vdavdov.bugtracking.task.mapper.TaskMapper;
import by.vdavdov.common.util.Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = TreeRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class TreeRestController {
    static final String REST_URL = "/api/tree";

    private final NodeMapper mapper;
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final SprintRepository sprintRepository;
    private final SprintMapper sprintMapper;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private static <T> List<TreeNode> toTree(List<T> list, Function<T, NodeTo> mapper) {
        List<NodeTo> nodes = list.stream()
                .map(mapper)
                .toList();
        return Util.makeTree(nodes, TreeNode::new);
    }

    @GetMapping("/projects")
    public List<TreeNode> getProjects() {
        log.info("get projects tree");
        return toTree(projectMapper.toToList(projectRepository.getAll()), mapper::fromProject);
    }

    @GetMapping("/projects/{projectId}/sprints")
    public List<TreeNode> getSprintsAndBacklog(@PathVariable long projectId) {
        log.info("get project {} sprints", projectId);
        List<SprintTo> sprintTos = new ArrayList<>(sprintMapper.toToList(sprintRepository.getAllByProject(projectId)));
        sprintTos.add(new SprintTo(0L, "Backlog", null, projectId));
        return toTree(sprintTos, mapper::fromSprint);
    }

    @GetMapping("/sprints/{sprintId}/tasks")
    public List<TreeNode> getSprintTasks(@PathVariable long sprintId) {
        log.info("get sprint {} tasks", sprintId);
        return toTree(taskMapper.toToList(taskRepository.findAllBySprintId(sprintId)), mapper::fromTask);
    }

    @GetMapping("/projects/{projectId}/backlog/tasks")
    public List<TreeNode> getBacklogTasks(@PathVariable long projectId) {
        log.info("get project {} backlog tasks", projectId);
        return toTree(taskMapper.toToList(taskRepository.findAllByProjectIdAndSprintIsNull(projectId)), mapper::fromTask);
    }
}
