package com.example.employee.TestUnitaire;


import com.socle.springboot.testUnitaire.TestJunitService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestUnitaireSpring {


    Logger logger = LoggerFactory.getLogger(TestUnitaireSpring.class.getName());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TestJunitService testJunitService;


    @BeforeEach
    void before() {
        String message = testJunitService.getMessage("bilel");
        assertNotNull(message);
        logger.info("@BeforeEach-executes before each test method in the class Success");
    }

    @AfterEach
    void after() {
        String message = testJunitService.getMessage("bilel");
        assertNotNull(message);
        logger.info("@AfterEach-executes after each test method in the class Success ");
    }


    @BeforeAll
    @DisplayName("before all map")
    void beforeAllTest() {
        Map<String, Integer[]> map = testJunitService.testArray(1, 2, 3);
        assertArrayEquals(map.get("arr1"), map.get("arr2"));
        logger.info("test before all success @BeforeAll ");
    }

    @DisplayName("test SumName")
    @RepeatedTest(2)
    void testSum() {
        assertEquals(20, testJunitService.getSum(10, 10));
        logger.info("Valid");
    }


    @Test
    @Tag("Tag test")
    void tagTest() {
        assertNull(testJunitService.getObject(null));
        logger.info("test tags 1 1 ===> true");
    }

    @Test
    @Timeout(1)
    void assumptionTest() {
        Assumptions.assumeTrue("test".equals("test"));
    }


    @Test
    @DisplayName("Assuming test")
    void testAssumingThat() {
        System.setProperty("ENV", "CD");
        assumingThat(
                "CI".equals(System.getProperty("Env")),
                () -> {
                    logger.info("Assuming that executable executed with success");
                });
        assertTrue(Math.random() > 0);
        logger.info("test Continue");
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestStream() {
        return IntStream.of(0, 3, 6, 9)
                .mapToObj(v ->
                        DynamicTest.dynamicTest(v + "is a multiple of 3", () -> assertEquals(0, v % 3)));
    }

    @ParameterizedTest
    @ValueSource(ints = {8, 4, 2, 5, 10})
    void testInArrays(int arg) {
        assertTrue(arg % 2 == 0);
        logger.info("arg =====> " + arg);
    }


    @Test
    @DisplayName("test display name")
    void testTag() {
        int x = 7;
        int y = 2;
        int delta = 5;
        assertEquals(x, y, delta);
        logger.info("test desplay name =====> true");

    }


    @Test
    public void testLists() {
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> l2 = new ArrayList<>(Arrays.asList(1, 0, 3, 4));
        Assertions.assertIterableEquals(l1, l2);
    }


    @Test
    void testException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException(("error message"));
        });

        logger.info("error ====> " + exception);
    }

    @Test
    public void testAssertAll() {

    }

}
