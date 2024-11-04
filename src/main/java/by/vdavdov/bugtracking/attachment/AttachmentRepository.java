package by.vdavdov.bugtracking.attachment;

import by.vdavdov.bugtracking.ObjectType;
import by.vdavdov.common.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface AttachmentRepository extends BaseRepository<Attachment> {

    @Query("SELECT a FROM Attachment a WHERE a.objectId =:objectId AND a.objectType =:objectType")
    List<Attachment> getAllForObject(long objectId, ObjectType objectType);
}
