package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private static final int MIN_LOGIN_LENGTH = 6;
    private static final int MIN_PASSWORD_LENGTH = 6;
    private static final int MIN_AGE = 18;

    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user == null) {
            throw new RegistrationException("User must not be null");
        }
        if (user.getLogin() == null) {
            throw new RegistrationException("Login must not be null");
        }
        if (user.getPassword() == null) {
            throw new RegistrationException("Password must not be null");
        }
        if (user.getAge() == null || user.getAge() < 0) {
            throw new RegistrationException("Age must be non-null and non-negative");
        }

        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationException("User with login '"
                    + user.getLogin() + "' already exists");
        }
        if (user.getLogin().length() < MIN_LOGIN_LENGTH) {
            throw new RegistrationException("Login must be at least "
                    + MIN_LOGIN_LENGTH + " characters long");
        }
        if (user.getPassword().length() < MIN_PASSWORD_LENGTH) {
            throw new RegistrationException("Password must be at least "
                    + MIN_PASSWORD_LENGTH + " characters long");
        }
        if (user.getAge() < MIN_AGE) {
            throw new RegistrationException("User must be at least "
                    + MIN_AGE + " years old");
        }

        storageDao.add(user);
        return user;
    }
}
