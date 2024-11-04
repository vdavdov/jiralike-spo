package by.vdavdov.bugtracking.attachment;

import by.vdavdov.bugtracking.attachment.to.AttachmentTo;
import by.vdavdov.common.BaseMapper;
import by.vdavdov.common.TimestampMapper;
import org.mapstruct.Mapper;

@Mapper(config = TimestampMapper.class)
public interface AttachmentMapper extends BaseMapper<Attachment, AttachmentTo> {
}
