package by.vdavdov.profile.internal;

import by.vdavdov.common.BaseRepository;
import by.vdavdov.profile.internal.model.Profile;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ProfileRepository extends BaseRepository<Profile> {
    default Profile getOrCreate(long id) {
        return findById(id).orElseGet(() -> new Profile(id));
    }
}
