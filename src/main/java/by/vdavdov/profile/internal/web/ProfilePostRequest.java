package by.vdavdov.profile.internal.web;

import by.vdavdov.profile.ContactTo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.Set;

@Data
public class ProfilePostRequest {
    private Set<@NotBlank String> mailNotifications;

    private @Valid ContactTo[] contacts;
}
