package by.vdavdov.login.internal.sociallogin;

import by.vdavdov.login.AuthUser;
import by.vdavdov.login.User;
import lombok.NonNull;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class CustomOAuth2User extends AuthUser implements OAuth2User {
    private final OAuth2User oauth2User;

    public CustomOAuth2User(@NonNull OAuth2User oauth2User, @NonNull User user) {
        super(user);
        this.oauth2User = oauth2User;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public String getName() {
        return super.getUsername();
    }
}
