package by.vdavdov.bugtracking.project.to;

import by.vdavdov.common.to.TitleTo;
import by.vdavdov.common.util.validation.Code;
import by.vdavdov.common.util.validation.Description;
import lombok.Getter;

@Getter
public class ProjectTo extends TitleTo {
    @Description
    String description;

    @Code
    String typeCode;

    Long parentId;

    public ProjectTo(Long id, String code, String title, String description, String typeCode, Long parentId) {
        super(id, code, title);
        this.description = description;
        this.typeCode = typeCode;
        this.parentId = parentId;
    }
}
