package com.israelsolha.vacinas.repositories;

import com.israelsolha.vacinas.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import javax.validation.ValidationException;

import static com.israelsolha.vacinas.prototypes.UserPrototype.aUser;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest public class UserRepositoryTest {

    @Autowired private UserRepository userRepository;

    @Test public void testIfUserIsSaved() {
        User userPrototype = aUser();
        User user = userRepository.save(userPrototype);
        assertNotNull(user);
        assertEquals(userPrototype.getName(), user.getName());
        assertNotNull(user.getUuid());
    }

    @Test public void testIfValidationsAreWorking() {
        User userPrototype = aUser();
        userPrototype.setCpf("123827");
        assertThrows(ValidationException.class,
                () -> userRepository.saveAndFlush(userPrototype));
    }

    @Test public void testUniqueEmailIsWorking() {
        User userPrototype = aUser();
        User userPrototype2 = aUser();
        userRepository.saveAndFlush(userPrototype);
        assertThrows(DataIntegrityViolationException.class,
                () -> userRepository.saveAndFlush(userPrototype2));

    }
}
