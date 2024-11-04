package by.vdavdov.mail.internal;

import by.vdavdov.common.internal.config.AppProperties;
import by.vdavdov.login.User;
import by.vdavdov.login.internal.UserMapper;
import by.vdavdov.login.internal.passwordreset.PasswordResetEvent;
import by.vdavdov.login.internal.verification.RegistrationConfirmEvent;
import by.vdavdov.mail.MailService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class MailListeners {
    private final UserMapper userMapper;
    private final MailService mailService;
    private final AppProperties appProperties;

    @EventListener
    public void confirmRegistration(RegistrationConfirmEvent event) {
        String confirmationUrl = appProperties.getHostUrl() + "/ui/register/confirm?token=" + event.token();
        User user = userMapper.toEntity(event.userto());
        mailService.sendToUserAsync("email-confirmation.html", user, Map.of("confirmationUrl", confirmationUrl));
    }

    @EventListener
    public void resetPassword(PasswordResetEvent event) {
        String resetUrl = appProperties.getHostUrl() + "/ui/password/change?token=" + event.token();
        mailService.sendToUserAsync("password-reset.html", event.user(), Map.of("resetUrl", resetUrl));
    }
}
