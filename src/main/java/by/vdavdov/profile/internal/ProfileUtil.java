package by.vdavdov.profile.internal;

import by.vdavdov.profile.ContactTo;
import by.vdavdov.ref.RefTo;
import by.vdavdov.ref.RefType;
import by.vdavdov.ref.ReferenceService;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class ProfileUtil {
    public static Set<String> maskToNotifications(long notifications) {
        Set<String> notificationsCodes = ReferenceService.getRefs(RefType.MAIL_NOTIFICATION).values().stream()
                .filter(ref -> (notifications & ref.getLongFromAux()) != 0)
                .map(RefTo::getCode)
                .collect(Collectors.toSet());
        return notificationsCodes.isEmpty() ? Set.of() : notificationsCodes;
    }

    public static long notificationsToMask(Set<String> notifications) {
        return notifications.stream()
                .map(code -> ReferenceService.getRefTo(RefType.MAIL_NOTIFICATION, code))
                .map(RefTo::getLongFromAux)
                .reduce(0L, (mask1, mask2) -> mask1 | mask2);
    }

    public static void checkContactsExist(Collection<ContactTo> contacts) {
        contacts.forEach(c -> ReferenceService.getRefTo(RefType.CONTACT, c.getCode()));
    }
}
