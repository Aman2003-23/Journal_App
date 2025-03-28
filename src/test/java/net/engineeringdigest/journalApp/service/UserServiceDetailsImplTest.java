package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;


import java.util.Collections;

import static org.mockito.Mockito.*;


@ActiveProfiles("dev")
 class UserServiceDetailsImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userServiceDetailsService;

    @Mock
    private UserRepository userRepository;


    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this); //we are talking about this
        // cklass used to work with implying springboot test in the beginning
        //injects all the movks inn this vlass
    }


    @Test
    void loadUserByUsernameTest() {
        // Mock the repository return value
        when(userRepository.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(net.engineeringdigest.journalApp.entity.User.builder()
                        .username("aman")
                        .password("232003")
                        .roles(Collections.singletonList("USER")) // Mock user-defined roles
                        .build());

        // Call the method
        UserDetails user = userServiceDetailsService.loadUserByUsername("aman");

        // Assert and validate the results
        Assertions.assertNotNull(user, "UserDetails should not be null");
        Assertions.assertEquals("aman", user.getUsername(), "Expected username does not match");
        Assertions.assertEquals("232003", user.getPassword(), "Expected password does not match");

        // Validate roles/authorities
        Assertions.assertTrue(user.getAuthorities()
                        .stream()
                        .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER")),
                "Expected ROLE_USER authority is not found.");
    }
}
