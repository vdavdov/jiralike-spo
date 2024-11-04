package by.vdavdov.login.internal;

import by.vdavdov.common.BaseHandler;
import by.vdavdov.login.User;
import by.vdavdov.login.UserTo;
import org.springframework.stereotype.Component;

import java.util.function.BinaryOperator;

@Component
public class UserHandler extends BaseHandler<User, UserTo, UserRepository, UserMapper> {
    public UserHandler(UserRepository repository, UserMapper mapper) {
        super(repository, mapper,
                UsersUtil::prepareForCreate,
                (BinaryOperator<User>) (user, dbUser) -> UsersUtil.prepareForUpdate(user, dbUser.getPassword()));
    }
}
