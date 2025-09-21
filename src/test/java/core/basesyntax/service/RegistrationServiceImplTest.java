package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.db.Storage;
import core.basesyntax.exception.RegistrationException;
import core.basesyntax.model.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RegistrationServiceImplTest {

    @Order(1)
    @Test
    void registerUserWithShortLogin() {
        User user = new User();
        user.setId(1L);
        user.setLogin("login");
        user.setPassword("passwordOfTheUser");
        user.setAge(21);
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Order(2)
    @Test
    void registerUserWithShortPassword() {
        User user = new User();
        user.setId(1L);
        user.setLogin("loginOfTheUser");
        user.setPassword("pass");
        user.setAge(21);
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Order(3)
    @Test
    void registerUserThatIsNotOldEnough() {
        User user = new User();
        user.setId(1L);
        user.setLogin("loginOfTheUser");
        user.setPassword("passwordOfTheUser");
        user.setAge(17);
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        assertThrows(RegistrationException.class,
                () -> registrationService.register(user));
    }

    @Order(4)
    @Test
    void registerUser() {
        User user = new User();
        user.setId(1L);
        user.setLogin("loginOfTheUser");
        user.setPassword("passwordOfTheUser");
        user.setAge(21);
        RegistrationServiceImpl registrationService = new RegistrationServiceImpl();
        registrationService.register(user);
        assertEquals(1, Storage.people.size());
    }
}
