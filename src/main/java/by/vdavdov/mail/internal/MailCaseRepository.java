package by.vdavdov.mail.internal;

import by.vdavdov.common.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface MailCaseRepository extends BaseRepository<MailCase> {
}
