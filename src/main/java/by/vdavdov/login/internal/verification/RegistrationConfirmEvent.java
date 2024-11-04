package by.vdavdov.login.internal.verification;

import by.vdavdov.common.AppEvent;
import by.vdavdov.login.UserTo;

public record RegistrationConfirmEvent(UserTo userto, String token) implements AppEvent {
}
