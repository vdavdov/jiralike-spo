package by.vdavdov.bugtracking.attachment.to;

import by.vdavdov.common.to.NamedTo;
import lombok.Getter;

@Getter
public class AttachmentTo extends NamedTo {
    public AttachmentTo(Long id, String name) {
        super(id, name);
    }
}
