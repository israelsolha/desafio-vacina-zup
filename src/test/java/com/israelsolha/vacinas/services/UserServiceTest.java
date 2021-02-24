package com.israelsolha.vacinas.services;

import com.israelsolha.vacinas.models.User;
import com.israelsolha.vacinas.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.israelsolha.vacinas.prototypes.UserPrototype.aUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) public class UserServiceTest {

    @Mock private UserRepository userRepository;

    @InjectMocks private UserService userService;

    @Test public void insert() {
        User aUser = aUser();
        when(userRepository.save(any())).thenReturn(aUser);
        User insertedUser = userService.insert(aUser);
        assertNotNull(insertedUser);
        assertEquals(insertedUser.getName(), aUser.getName());
    }

}
