package com.example.employee.TestUnitaire;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Nested Test")
public class NestedTest {

    @Test
    @DisplayName("First Test")
    public void firstTest() {

    }

    @Nested
    @DisplayName("Second Test")
    class SecondTest {

        @Test
        public void secondTest() {
        }

        @Nested
        @DisplayName("Third Test")
        class ThirdTest {

            @Test
            void thirdTest() {

            }

            @Test
            public void test() {

            }

        }
    }
}
