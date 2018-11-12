package com.example;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/*
 * Test-class for russian localization
 */
public class GreetingTestRu {
    private LocalTime morning = LocalTime.of(6, 0, 0);
    private LocalTime dayTime = LocalTime.of(9, 0, 0);
    private LocalTime evening = LocalTime.of(19, 0, 0);

    private static Greeting greeting;
    private static PrintStream out;
    private static ResourceBundle bundle;

    @BeforeClass
    public static void init() {
        out = mock(PrintStream.class);
        System.setOut(out);
        Locale.setDefault(new Locale("ru", "RU"));
        bundle = ResourceBundle.getBundle("greetings");
        greeting = new Greeting();
    }

    //Comment Ignore while testing print method + uncomment Greeting.setNow()
    @Test
//    @Ignore
    public void printNight() {
        greeting.setNow(morning.minusHours(3));
        greeting.print();
        verify(out).println(toUTF8(bundle.getString("good_night")));
    }

    @Test
//    @Ignore
    public void printMorning() {
        greeting.setNow(morning.plusHours(1));
        greeting.print();
        verify(out).println(toUTF8(bundle.getString("good_morning")));
    }

    @Test
//    @Ignore
    public void printDay() {
        greeting.setNow(dayTime);
        greeting.print();
        verify(out).println(toUTF8(bundle.getString("good_day")));
    }

    @Test
//    @Ignore
    public void printEvening() {
        greeting.setNow(evening.plusHours(3));
        greeting.print();
        verify(out).println(toUTF8(bundle.getString("good_evening")));
    }

    //Set Charset to UTF-8
    private String toUTF8(String s){
        return new String(s.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    }
}
