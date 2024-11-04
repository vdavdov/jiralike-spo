package by.vdavdov.bugtracking.report;

import by.vdavdov.bugtracking.task.Task;
import by.vdavdov.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ReportRepository extends BaseRepository<Task> {
    @Query("SELECT new by.vdavdov.bugtracking.report.TaskSummary(r.title, COUNT(t.id)) " +
            "FROM Reference r LEFT JOIN Task t " +
            "ON r.code = t.statusCode AND t.sprint.id = :sprintId " +
            "WHERE r.refType = :#{T(by.vdavdov.ref.RefType).TASK_STATUS} " +
            "GROUP BY r.title, r.id " +
            "ORDER BY r.id")
    List<TaskSummary> getTaskSummaries(long sprintId);
}
