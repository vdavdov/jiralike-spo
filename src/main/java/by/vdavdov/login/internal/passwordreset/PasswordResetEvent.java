package by.vdavdov.login.internal.passwordreset;

import by.vdavdov.common.AppEvent;
import by.vdavdov.login.User;

public record PasswordResetEvent(User user, String token) implements AppEvent {
}
