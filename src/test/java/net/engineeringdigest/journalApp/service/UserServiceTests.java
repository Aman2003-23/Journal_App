package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserRepository userRepository;
    @Disabled
    @Test
    public void testfindByUserName() {


        User user = userRepository.findByUsername("aman");
        assertTrue(user.getJournalEntries().isEmpty());
    }



    ///parameterized testing
    @ParameterizedTest
    @CsvSource({
            "1,1,2",

            "3,3,6"
    })

    public void test(int a,int c,int expected){
        assertEquals(expected,a+c,"failed for "+expected) ;
    }
}
