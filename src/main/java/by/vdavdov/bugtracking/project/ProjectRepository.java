package by.vdavdov.bugtracking.project;

import by.vdavdov.common.TimestampRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ProjectRepository extends TimestampRepository<Project> {
    @Query("SELECT p FROM Project p LEFT JOIN FETCH p.parent WHERE p.id =:id")
    Optional<Project> findFullById(long id);
}
