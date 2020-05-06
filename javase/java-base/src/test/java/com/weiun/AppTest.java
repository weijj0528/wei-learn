package com.weiun;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void intCovertTest() {
        Integer integer = Integer.valueOf("0001");
        System.out.println(integer);
    }
}
