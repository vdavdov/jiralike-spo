package by.vdavdov.bugtracking.sprint.to;

import by.vdavdov.common.HasCode;
import by.vdavdov.common.to.CodeTo;
import by.vdavdov.common.util.validation.Code;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SprintTo extends CodeTo implements HasCode {
    @Code
    String statusCode;
    @NotNull
    Long projectId;

    public SprintTo(Long id, String code, String statusCode, Long projectId) {
        super(id, code);
        this.statusCode = statusCode;
        this.projectId = projectId;
    }
}
