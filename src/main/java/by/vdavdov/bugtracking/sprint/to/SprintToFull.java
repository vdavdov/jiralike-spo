package by.vdavdov.bugtracking.sprint.to;

import by.vdavdov.common.to.CodeTo;
import lombok.Value;

@Value
public class SprintToFull extends SprintTo {
    CodeTo project;

    public SprintToFull(Long id, String code, String statusCode, CodeTo project) {
        super(id, code, statusCode, project.getId());
        this.project = project;
    }
}
