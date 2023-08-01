package com.example.powerAdmin;

import com.example.powerAdmin.emojiTest.entity.Country;
import com.example.powerAdmin.emojiTest.entity.Leader;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PowerAdminApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void test5() throws JsonProcessingException {
        Leader leader = new Leader("忽必烈", 79, "蒙古族");
        Country country = new Country("元朝", leader);
        System.out.println(leader);
        System.out.println(country);
    }
}
