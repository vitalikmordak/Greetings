package com.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class GreetingTest {
    private LocalTime morning = LocalTime.of(6, 0, 0);
    private LocalTime dayTime = LocalTime.of(9, 0, 0);
    private LocalTime evening = LocalTime.of(19, 0, 0);
    private LocalTime night = LocalTime.of(23, 0, 0);
    private LocalTime now;

    private static Greeting greeting;
    private static PrintStream out;
    private static ResourceBundle bundle;

    @BeforeClass
    public static void init() {
        out = mock(PrintStream.class);
        System.setOut(out);
        bundle = ResourceBundle.getBundle("greetings");
        greeting = new Greeting();
    }

    @Test
    public void loggerTest() {
        Logger logger = LogManager.getLogger(GreetingTest.class);
        logger.info("Test message");
    }

    //Comment Ignore while testing print method + uncomment Greeting.setNow()
    @Test
//    @Ignore
    public void printNight() {
        greeting.setNow(morning.minusHours(3));
        greeting.print();
        verify(out).println(bundle.getString("good_night"));
    }

    @Test
//    @Ignore
    public void printMorning() {
        greeting.setNow(morning.plusHours(1));
        greeting.print();
        verify(out).println(bundle.getString("good_morning"));
    }

    @Test
//    @Ignore
    public void printDay() {
        greeting.setNow(dayTime);
        greeting.print();
        verify(out).println(bundle.getString("good_day"));
    }

    @Test
//    @Ignore
    public void printEvening() {
        greeting.setNow(evening.plusHours(3));
        greeting.print();
        verify(out).println(bundle.getString("good_evening"));
    }

    @Test
    public void between() {
        LocalTime now = LocalTime.of(7, 5, 0);
        assertTrue(now.isAfter(morning) && now.isBefore(dayTime));
        assertFalse(now.isAfter(dayTime) && now.isBefore(evening));
    }

    @Test
    public void beetween1() {
        now = LocalTime.of(20, 20, 0);
        assertTrue(now.isAfter(evening) && now.isBefore(night));
    }

    @Test
    public void beetween2() {
        now = LocalTime.of(4, 47, 0);
        assertFalse(now.isAfter(night) && now.isBefore(morning));
        greeting.setNow(now);
        assertTrue(greeting.between(night, morning));
    }

    @Test
    public void nowTimeNull() {
        assertNull(now);
        assertNotNull(greeting.getNow());
    }
}